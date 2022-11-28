package com.nashss.se.partyplaylist.activity;
import com.nashss.se.partyplaylist.activity.requests.CreatePlaylistRequest;
import com.nashss.se.partyplaylist.activity.results.CreatePlaylistResult;

import com.nashss.se.partyplaylist.converters.ModelConverter;
import com.nashss.se.partyplaylist.dynamodb.PlaylistDao;

import com.nashss.se.partyplaylist.dynamodb.models.Playlist;
import com.nashss.se.partyplaylist.models.PlaylistModel;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Implementation of the CreatePlaylistActivity for the MusicPlaylistService's CreatePlaylist API.
 * <p>
 * This API allows the customer to create a new playlist with no songs.
 */
public class CreatePlaylistActivity {
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

        Playlist newPlaylist = new Playlist();

        newPlaylist.setPlaylistId(createPlaylistRequest.getPlaylistId());
        newPlaylist.setPlaylistName(createPlaylistRequest.getPlaylistName());
        newPlaylist.setSongs(new ArrayList<>());
        newPlaylist.setGuests(null);

        playlistDao.savePlaylist(newPlaylist);

        PlaylistModel playlistModel = new ModelConverter().toPlaylistModel(newPlaylist);
        return CreatePlaylistResult.builder()
                .withPlaylist(playlistModel)
                .build();
    }
}
