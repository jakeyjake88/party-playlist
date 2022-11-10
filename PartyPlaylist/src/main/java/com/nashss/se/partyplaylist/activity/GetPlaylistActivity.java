package com.nashss.se.partyplaylist.activity;

import com.nashss.se.partyplaylist.activity.requests.GetPlaylistRequest;
import com.nashss.se.partyplaylist.activity.results.GetPlaylistResult;
import com.nashss.se.partyplaylist.converters.old.ModelConverter;
import com.nashss.se.partyplaylist.dynamodb.PlaylistDao;
import com.nashss.se.partyplaylist.dynamodb.models.Playlist;
import com.nashss.se.partyplaylist.models.PlaylistModel;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Accesses data for a playlist using {@link PlaylistModel} to represent the model in DynamoDB.
 */

@Singleton
public class GetPlaylistActivity {

    private final PlaylistDao playlistDao;

    /**
     * Instantiates a PlaylistDao object.
     *
     * @param playlistDao PlaylistDao to access the playlist table.
     */

    @Inject
    public GetPlaylistActivity(PlaylistDao playlistDao) {
        this.playlistDao = playlistDao;
    }

    /**
     * This method handles the incoming request by retrieving the playlist from the database.
     * <p>
     * It then returns the playlist.
     * <p>
     * If the playlist does not exist, this should throw a PlaylistNotFoundException.
     *
     * @param getPlaylistRequest request object containing the playlist ID
     * @return getPlaylistResult result object containing the API defined {@link PlaylistModel}
     */
    public GetPlaylistResult handleRequest(final GetPlaylistRequest getPlaylistRequest) {
//        log.info("Received GetPlaylistRequest {}", getPlaylistRequest);

        String requestedId = getPlaylistRequest.getId();
        Playlist playlist = playlistDao.getPlaylist(requestedId);

        PlaylistModel playlistModel = new ModelConverter().toPlaylistModel(playlist);

        return GetPlaylistResult.builder().withPlaylist(playlistModel).build();
    }
}
