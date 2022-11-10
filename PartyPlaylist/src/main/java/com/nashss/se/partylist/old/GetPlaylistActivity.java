package com.nashss.se.partylist.old;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Accesses data for a playlist using {@link PlaylistModel} to represent the model in DynamoDB.
 */

@Singleton
public class GetPlaylistActivity {

    private final DynamoDBMapper dynamoDBMapper;

    private final PlaylistDao playlistDao;

    /**
     * Instantiates a PlaylistDao object.
     *
     * @param dynamoDbMapper the {@link DynamoDBMapper} used to interact with the playlists table
     * @param metricsPublisher the {@link MetricsPublisher} used to record metrics.
     */

    @Inject
    public GetPlaylistActivity(Playlist Dao playlistDao) {
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

//        PlaylistModel playlistModel = new ModelConverter().toPlaylistModel(playlist);

        return GetPlaylistResult.builder().withplaylist(playlistModel).build();
    }
}
