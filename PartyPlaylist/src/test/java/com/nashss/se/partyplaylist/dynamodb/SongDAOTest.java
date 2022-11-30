package com.nashss.se.partyplaylist.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.nashss.se.partyplaylist.dynamodb.models.Song;
import com.nashss.se.partyplaylist.exceptions.SongNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class SongDAOTest {

    @Mock
    private DynamoDBMapper dynamoDBMapper;
    @Mock
    private SongDAO songDAO;

    @BeforeEach
    void setup() {
        openMocks(this);
        songDAO = new SongDAO(dynamoDBMapper);
    }

    @Test
    public void getSong_withValidSongArtistAndSongTitle_returnsSong() {
        String songArtist = "Nickelback";
        String songTitle = "Photograph";

        when(dynamoDBMapper.load(Song.class, songTitle, songArtist)).thenReturn(new Song());

       Song song = songDAO.getSong(songArtist, songTitle);

       assertNotNull(song);
       verify(dynamoDBMapper).load(Song.class, songTitle, songArtist);

    }

    @Test
    public void getSong_withInvalidSongArtist_throwsSongNotFoundException() {
        String invalidArtist = "Cher";
        String invalidTitle = "Believe";

        when(dynamoDBMapper.load(Song.class, invalidArtist, invalidTitle)).thenReturn(null);

        assertThrows(SongNotFoundException.class, ()-> songDAO.getSong(invalidArtist, invalidTitle));

    }
}
