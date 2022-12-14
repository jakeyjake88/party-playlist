package com.nashss.se.partyplaylist.dynamodb;

import com.nashss.se.partyplaylist.dynamodb.models.Playlist;
import com.nashss.se.partyplaylist.exceptions.PlaylistNotFoundException;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

/**
 * Accesses data for a playlist using {@link Playlist} to represent the model in DynamoDB.
 */
public class PlaylistDao {
    public static final String PLAYLIST_NAME_INDEX = "PlaylistNameIndex";
    private final DynamoDBMapper dynamoDBMapper;

    /**
     * Instantiates a PlaylistDao object.
     *
     * @param dynamoDbMapper the {@link DynamoDBMapper} used to interact with the playlists table
     *
     */

    @Inject
    public PlaylistDao(DynamoDBMapper dynamoDbMapper) {
        this.dynamoDBMapper = dynamoDbMapper;
    }

    /**
     * Returns the {@link Playlist} corresponding to the specified id.
     *
     * @param id the Playlist ID
     * @return the stored Playlist, or null if none was found.
     */

    public Playlist getPlaylist(String id) {
        Playlist playlist = this.dynamoDBMapper.load(Playlist.class, id);

        if (playlist == null) {
            throw new PlaylistNotFoundException("Could not find playlist with id " + id);
        }

        return playlist;
    }

    /**
     * Returns the {@link Playlist} corresponding to the specified playlistName.
     *
     * @param playlistName the Playlist Name
     * @return the stored Playlist, or null if none was found.
     */

    public Playlist getPlaylistWithPlaylistName(String playlistName) {
        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":playlistName", new AttributeValue().withS(playlistName));
        DynamoDBQueryExpression<Playlist> queryExpression = new DynamoDBQueryExpression<Playlist>()
                .withIndexName(PLAYLIST_NAME_INDEX)
                .withConsistentRead(false)
                .withKeyConditionExpression("playlistName = :playlistName")
                .withExpressionAttributeValues(valueMap);

        PaginatedQueryList<Playlist> playlist = dynamoDBMapper.query(Playlist.class, queryExpression);

        if (playlist.isEmpty()) {
            throw new PlaylistNotFoundException(
                    String.format("Could not find playlist '%s'. Please try again.", playlistName));
        }

        return playlist.get(0);
    }

    /**
     * Returns a saved/updated {@link Playlist} in Database.
     *
     * @param playlist to be saved/updated
     * @return the up-to-date playlist
     */

    public Playlist savePlaylist(Playlist playlist) {
        this.dynamoDBMapper.save(playlist);
        return playlist;
    }

    /**
     * Returns the host associated with the {@link Playlist} in Database.
     *
     * @param id of the playlist
     * @return the current host
     */
    public String getHost(String id) {
        Playlist playlist = this.dynamoDBMapper.load(Playlist.class, id);

        if (playlist == null) {
            throw new PlaylistNotFoundException(String.format("Playlist associated with id: %s does not exist", id));
        }

        return playlist.getHost();
    }
}
