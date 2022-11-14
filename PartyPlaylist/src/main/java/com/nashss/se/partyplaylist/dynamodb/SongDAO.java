package com.nashss.se.partyplaylist.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.nashss.se.partyplaylist.SongNotFoundException;
import com.nashss.se.partyplaylist.dynamodb.models.Song;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Accesses data for a song using {@link Song} to represent the model in DynamoDB.
 */
@Singleton
public class SongDAO {

    private final DynamoDBMapper dynamoDbMapper;

    /**
     * Instantiates an SongDAO object.
     *
     * @param dynamoDbMapper the {@link DynamoDBMapper} used to interact with the songs table
     */
    @Inject
    public SongDAO(DynamoDBMapper dynamoDbMapper) {
            this.dynamoDbMapper = dynamoDbMapper;
        }

    /**
     * Retrieves a Song by songID.
     *
     * If not found, throws SongNotFoundException.
     *
     * @param songId the songId to look up
     * @return The corresponding Song if found
     */
    public Song getSong(String songId) {
        Song song = dynamoDbMapper.load(Song.class, songId);
        if (null == song) {
            throw new SongNotFoundException(
                    String.format("Could not find Song with songId %s", songId));
        }

        return song;
    }
}
