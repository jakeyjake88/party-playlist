package com.nashss.se.partyplaylist.activity;

import com.nashss.se.partyplaylist.activity.requests.GetPlaylistByNameRequest;
import com.nashss.se.partyplaylist.activity.results.GetPlaylistByNameResult;

import com.nashss.se.partyplaylist.converters.ModelConverter;

import com.nashss.se.partyplaylist.dynamodb.PlaylistDao;
import com.nashss.se.partyplaylist.dynamodb.models.Playlist;

import com.nashss.se.partyplaylist.models.PlaylistModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Accesses data for a playlist using {@link PlaylistModel} to represent the model in DynamoDB.
 */

@Singleton
public class GetPlaylistByNameActivity {

    private final Logger log = LogManager.getLogger();
    private final PlaylistDao playlistDao;

    /**
     * Instantiates a PlaylistDao object.
     *
     * @param playlistDao PlaylistDao to access the playlist table.
     */

    @Inject
    public GetPlaylistByNameActivity(PlaylistDao playlistDao) {
        this.playlistDao = playlistDao;
    }

    /**
     * This method handles the incoming request by retrieving the playlist from the database.
     * <p>
     * It then returns the playlist.
     * <p>
     * If the playlist does not exist, this should throw a PlaylistNotFoundException.
     *
     * @param getPlaylistByNameRequest request object containing the playlist name
     * @return getPlaylistResult result object containing the API defined {@link PlaylistModel}
     */
    public GetPlaylistByNameResult handleRequest(final GetPlaylistByNameRequest getPlaylistByNameRequest) {
        log.info("Received GetPlaylistRequest {}", getPlaylistByNameRequest);

        String playlistName = getPlaylistByNameRequest.getPlaylistName();
        Playlist playlist = playlistDao.getPlaylistWithPlaylistName(playlistName);

        PlaylistModel playlistModel = new ModelConverter().toPlaylistModel(playlist);

        return GetPlaylistByNameResult.builder().withPlaylist(playlistModel).build();
    }
}
