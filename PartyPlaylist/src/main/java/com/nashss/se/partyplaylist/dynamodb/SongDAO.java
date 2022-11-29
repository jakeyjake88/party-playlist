package com.nashss.se.partyplaylist.dynamodb;

import com.nashss.se.partyplaylist.dynamodb.models.Song;
import com.nashss.se.partyplaylist.exceptions.SongNotFoundException;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

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
     * @param songTitle the songTitle to look up
     * @param songArtist the songArtist to look up
     * @return The corresponding Song if found
     */
    public Song getSong(String songArtist, String songTitle) {
        Song song = dynamoDbMapper.load(Song.class, songArtist, songTitle);
        if (song == null) {
            throw new SongNotFoundException(
                    String.format("Could not find Song with Title: '%s' by Artist: '%s'", songTitle, songArtist));
        }

        return song;
    }

    /**
     * Removes a song object from the database.
     * @param song to be deleted
     */
    public void removeSong(Song song) {
        dynamoDbMapper.delete(song);
    }
}
