package com.nashss.se.partyplaylist.activity;

import com.nashss.se.partyplaylist.activity.requests.GetPlaylistByIdRequest;
import com.nashss.se.partyplaylist.activity.results.GetPlaylistByIdResult;
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
public class GetPlaylistByIdActivity {

    private final Logger log = LogManager.getLogger();
    private final PlaylistDao playlistDao;

    /**
     * Instantiates a PlaylistDao object.
     *
     * @param playlistDao PlaylistDao to access the playlist table.
     */

    @Inject
    public GetPlaylistByIdActivity(PlaylistDao playlistDao) {
        this.playlistDao = playlistDao;
    }

    /**
     * This method handles the incoming request by retrieving the playlist from the database.
     * <p>
     * It then returns the playlist.
     * <p>
     * If the playlist does not exist, this should throw a PlaylistNotFoundException.
     *
     * @param getPlaylistByIdRequest request object containing the playlist Id
     * @return getPlaylistResult result object containing the API defined {@link PlaylistModel}
     */
    public GetPlaylistByIdResult handleRequest(final GetPlaylistByIdRequest getPlaylistByIdRequest) {
        log.info("Received GetPlaylistRequest {}", getPlaylistByIdRequest);

        String playlistId = getPlaylistByIdRequest.getPlaylistId();
        Playlist playlist = playlistDao.getPlaylist(playlistId);

        PlaylistModel playlistModel = new ModelConverter().toPlaylistModel(playlist);

        return GetPlaylistByIdResult.builder().withPlaylist(playlistModel).build();
    }
}
