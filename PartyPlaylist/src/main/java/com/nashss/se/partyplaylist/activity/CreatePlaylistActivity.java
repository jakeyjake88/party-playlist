package com.nashss.se.partyplaylist.activity;

import com.nashss.se.partyplaylist.activity.requests.CreatePlaylistRequest;
import com.nashss.se.partyplaylist.activity.results.CreatePlaylistResult;

import com.nashss.se.partyplaylist.converters.ModelConverter;
import com.nashss.se.partyplaylist.dynamodb.PlaylistDao;
import com.nashss.se.partyplaylist.dynamodb.models.Playlist;
import com.nashss.se.partyplaylist.exceptions.PlaylistAlreadyExistsException;
import com.nashss.se.partyplaylist.models.PlaylistModel;

import com.nashss.se.projectresources.music.playlist.servic.util.MusicPlaylistServiceUtils;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Implementation of the CreatePlaylistActivity for the MusicPlaylistService's CreatePlaylist API.
 * <p>
 * This API allows the customer to create a new playlist with no songs.
 */
public class CreatePlaylistActivity {

    private final Logger log = LogManager.getLogger();
    private final PlaylistDao playlistDao;

    /**
     * Instantiates a new CreatePlaylistActivity object.
     *
     * @param playlistDao PlaylistDao to access the playlists table.
     */
    @Inject
    public CreatePlaylistActivity(PlaylistDao playlistDao) {
        this.playlistDao = playlistDao;
    }

    /**
     * This method handles the incoming request by persisting a new playlist
     * with the provided playlist name and customer ID from the request.
     * <p>
     * It then returns the newly created playlist.
     * <p>
     * If the provided playlist name or customer ID has invalid characters, throws an
     * InvalidAttributeValueException
     *
     * @param createPlaylistRequest request object containing the playlist name and customer ID
     *                              associated with it
     * @return createPlaylistResult result object containing the API defined {@link PlaylistModel}
     */
    public CreatePlaylistResult handleRequest(final CreatePlaylistRequest createPlaylistRequest) {
        log.info("Received CreatePlaylistRequest {} ", createPlaylistRequest);

        Playlist playlist = playlistDao.getPlaylistWithPlaylistName(createPlaylistRequest.getPlaylistName());

        if (playlist != null) {
            throw new PlaylistAlreadyExistsException(
                    String.format("'%s' already exists. Please choose another name.",
                            createPlaylistRequest.getPlaylistName()));
        }

        Playlist newPlaylist = new Playlist();

        newPlaylist.setPlaylistId(MusicPlaylistServiceUtils.generatePlaylistId());
        newPlaylist.setPlaylistName(createPlaylistRequest.getPlaylistName());
        newPlaylist.setSongs(new ArrayList<>());
        newPlaylist.setGuests(null);
        newPlaylist.setHost(createPlaylistRequest.getHost());

        playlistDao.savePlaylist(newPlaylist);

        PlaylistModel playlistModel = new ModelConverter().toPlaylistModel(newPlaylist);
        return CreatePlaylistResult.builder()
                .withPlaylist(playlistModel)
                .build();

    }

}
