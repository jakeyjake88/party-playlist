package com.nashss.se.partyplaylist.activity;

import com.nashss.se.partyplaylist.activity.requests.AddSongToPlaylistRequest;
import com.nashss.se.partyplaylist.activity.results.AddSongToPlaylistResult;
import com.nashss.se.partyplaylist.exceptions.ArtistLimitException;
import com.nashss.se.partyplaylist.dynamodb.PlaylistDao;
import com.nashss.se.partyplaylist.dynamodb.SongDAO;
import com.nashss.se.partyplaylist.dynamodb.models.Playlist;
import com.nashss.se.partyplaylist.dynamodb.models.PlaylistEntry;
import com.nashss.se.partyplaylist.dynamodb.models.Song;

import com.nashss.se.partyplaylist.exceptions.SongAlreadyOnPlaylistException;
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

    @Test
    public void getArtistCount_playlistWithArtistLimit_throwsArtistLimitException() {
        String songArtist = "Nickelback";
        String songTitle1 = "Photograph";
        String songTitle2 = "How U Remind Me";
        String songTitle3 = "Too Bad";
        String songTitle4 = "Rock Star";
        String id = "01";


        Song song1 = new Song();
        song1.setSongArtist(songArtist);
        song1.setSongTitle(songTitle1);

        Song song2 = new Song();
        song2.setSongArtist(songArtist);
        song2.setSongTitle(songTitle2);

        Song song3 = new Song();
        song3.setSongArtist(songArtist);
        song3.setSongTitle(songTitle3);

        Song song4 = new Song();
        song4.setSongArtist(songArtist);
        song4.setSongTitle(songTitle4);

        PlaylistEntry pe1 = new PlaylistEntry(song1);
        PlaylistEntry pe2 = new PlaylistEntry(song2);
        PlaylistEntry pe3 = new PlaylistEntry(song3);
        PlaylistEntry pe4 = new PlaylistEntry(song4);

        List<PlaylistEntry> peList = new ArrayList<>(List.of(pe1, pe2, pe3));

        Playlist playlist = new Playlist();
        playlist.setPlaylistId(id);
        playlist.setSongs(peList);

        when(playlistDao.getPlaylist(id)).thenReturn(playlist);
        when(songDAO.getSong(songTitle4, songArtist)).thenReturn(song4);


        AddSongToPlaylistRequest request = AddSongToPlaylistRequest.builder()
                .withPlaylistId(playlist.getPlaylistId())
                .withSongTitle(pe4.getSongTitle())
                .withSongArtist(pe4.getSongArtist())
                .build();

        assertThrows(ArtistLimitException.class, ()-> addSongToPlaylistActivity.handleRequest(request));

    }

    @Test
    public void isSongOnList_playlistWithSongAlreadyOnList_throwsSongAlreadyOnPlaylistException() {
        String songArtist = "Nickelback";
        String songTitle1 = "Photograph";
        String songTitle2 = "Photograph";
        String id = "01";


        Song song1 = new Song();
        song1.setSongArtist(songArtist);
        song1.setSongTitle(songTitle1);

        Song song2 = new Song();
        song2.setSongArtist(songArtist);
        song2.setSongTitle(songTitle2);

        PlaylistEntry pe1 = new PlaylistEntry(song1);
        PlaylistEntry pe2 = new PlaylistEntry(song2);

        List<PlaylistEntry> peList = new ArrayList<>(List.of(pe1));

        Playlist playlist = new Playlist();
        playlist.setPlaylistId(id);
        playlist.setSongs(peList);

        when(playlistDao.getPlaylist(id)).thenReturn(playlist);
        when(songDAO.getSong(songTitle2, songArtist)).thenReturn(song2);


        AddSongToPlaylistRequest request = AddSongToPlaylistRequest.builder()
                .withPlaylistId(playlist.getPlaylistId())
                .withSongTitle(pe2.getSongTitle())
                .withSongArtist(pe2.getSongArtist())
                .build();

        assertThrows(SongAlreadyOnPlaylistException.class, ()-> addSongToPlaylistActivity.handleRequest(request));

    }
}
