package com.nashss.se.partyplaylist.activity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.nashss.se.partyplaylist.activity.requests.AddSongToPlaylistRequest;
import com.nashss.se.partyplaylist.activity.results.AddSongToPlaylistResult;
import com.nashss.se.partyplaylist.dynamodb.PlaylistDao;
import com.nashss.se.partyplaylist.dynamodb.SongDAO;
import com.nashss.se.partyplaylist.dynamodb.models.Playlist;
import com.nashss.se.partyplaylist.dynamodb.models.PlaylistEntry;
import com.nashss.se.partyplaylist.dynamodb.models.Song;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class AddSongToPlaylistActivityTest {
    @Mock
    private PlaylistDao playlistDao;
    @Mock
    private SongDAO songDAO;
    private AddSongToPlaylistActivity addSongToPlaylistActivity;

    @BeforeEach
    void setup() {
        openMocks(this);
        addSongToPlaylistActivity = new AddSongToPlaylistActivity(playlistDao, songDAO);
    }

    @Test
    public void handleRequest_addSong_addsSongToPlaylist() {
        String songArtist = "Nickelback";
        String songTitle = "Photograph";
        String songId = "01";
        String id = "01";
        String host = "host";

        Song song = new Song();
        song.setSongArtist(songArtist);
        song.setSongTitle(songTitle);
        song.setSongId(songId);

        PlaylistEntry pe = new PlaylistEntry(song);

        List<PlaylistEntry> peList = new ArrayList<>();

        Playlist playlist = new Playlist();
        playlist.setPlaylistId(id);
        playlist.setHost(host);
        playlist.setSongs(peList);

        when(playlistDao.getPlaylist(id)).thenReturn(playlist);
        when(songDAO.getSong(songTitle, songArtist)).thenReturn(song);


        AddSongToPlaylistRequest request = AddSongToPlaylistRequest.builder()
                .withPlaylistId(playlist.getPlaylistId())
                .withSongTitle(pe.getSongTitle())
                .withSongArtist(pe.getSongArtist())
                .build();

        when(playlistDao.savePlaylist(playlist)).thenReturn(playlist);
        AddSongToPlaylistResult result = addSongToPlaylistActivity.handleRequest(request);


        verify(playlistDao).savePlaylist(any(Playlist.class));
        assertEquals(playlist.getSongs().get(0), pe);
        assertFalse(result.getSongList().isEmpty());
        assertNotNull(result.getSongList());
    }
}
