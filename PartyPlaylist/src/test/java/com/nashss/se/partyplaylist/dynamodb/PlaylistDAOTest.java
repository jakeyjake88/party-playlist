package com.nashss.se.partyplaylist.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.nashss.se.partyplaylist.dynamodb.models.Playlist;
import com.nashss.se.partyplaylist.exceptions.PlaylistNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class PlaylistDAOTest {

    @Mock
    private DynamoDBMapper dynamoDBMapper;

    private PlaylistDao playlistDao;

    @BeforeEach
    void setup() {
        openMocks(this);
        playlistDao = new PlaylistDao(dynamoDBMapper);
    }

    @Test
    public void getPlaylist_withValidPlaylistId_returnsPlaylist() {

        String playlistId = "01";
        when(dynamoDBMapper.load(Playlist.class, playlistId)).thenReturn(new Playlist());

        Playlist playlist = playlistDao.getPlaylist(playlistId);

        assertNotNull(playlist);
        verify(dynamoDBMapper).load(Playlist.class, playlistId);

    }

    @Test
    public void getPlaylist_withInvalidPlaylistId_throwsPlaylistNotFoundException() {
        String invalidId = "666";
        when(dynamoDBMapper.load(Playlist.class, invalidId)).thenReturn(null);

        assertThrows(PlaylistNotFoundException.class, ()-> playlistDao.getPlaylist(invalidId));

    }

    @Test
    public void savePlaylist_withNonNullPlaylist_savesOrUpdatesPlaylist() {

        String playlistId = "01";
        when(dynamoDBMapper.load(Playlist.class, playlistId)).thenReturn(new Playlist());

        Playlist playlist = playlistDao.getPlaylist(playlistId);
        playlist.setPlaylistName("New Name");
        playlist.setHost("New Host");

        Playlist newPlaylist = playlistDao.savePlaylist(playlist);
        when(dynamoDBMapper.load(Playlist.class, playlistId)).thenReturn(newPlaylist);

        newPlaylist = playlistDao.getPlaylist(playlistId);

        assertEquals(newPlaylist, playlist);
    }

    @Test
    public void getHost_withValidPlaylistId_returnsHost() {
        String playlistId = "01";
        String host = "Michael McDonald";

        when(dynamoDBMapper.load(Playlist.class, playlistId)).thenReturn(new Playlist());

        Playlist playlist = playlistDao.getPlaylist(playlistId);
        playlist.setHost(host);

        assertEquals(playlistDao.getHost(playlistId), host);

    }

    @Test
    public void getHost_withInvalidPlaylistId_throwsPlaylistNotFoundException() {
        String invalidId = "666";

        when(dynamoDBMapper.load(Playlist.class, invalidId)).thenReturn(null);

        assertThrows(PlaylistNotFoundException.class, ()-> playlistDao.getHost(invalidId));
    }


}
