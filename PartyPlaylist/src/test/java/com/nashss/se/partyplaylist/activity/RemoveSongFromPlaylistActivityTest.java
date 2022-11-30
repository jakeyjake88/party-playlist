package com.nashss.se.partyplaylist.activity;

import com.nashss.se.partyplaylist.activity.requests.RemoveSongFromPlaylistRequest;
import com.nashss.se.partyplaylist.activity.results.RemoveSongFromPlaylistResult;
import com.nashss.se.partyplaylist.dynamodb.PlaylistDao;
import com.nashss.se.partyplaylist.dynamodb.SongDAO;
import com.nashss.se.partyplaylist.dynamodb.models.Playlist;
import com.nashss.se.partyplaylist.dynamodb.models.PlaylistEntry;
import com.nashss.se.partyplaylist.dynamodb.models.Song;
import com.nashss.se.partyplaylist.test.helper.PlaylistTestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class RemoveSongFromPlaylistActivityTest {

    @Mock
    private PlaylistDao playlistDao;
    private RemoveSongFromPlaylistActivity removeSongFromPlaylistActivity;

    @BeforeEach
    void setup() {
        openMocks(this);
        removeSongFromPlaylistActivity = new RemoveSongFromPlaylistActivity(playlistDao);
    }

    @Test
    public void handleRequest_removeSong_removesSongFromPlaylist() {

        String songArtist = "Nickelback";
        String songTitle = "Photograph";
        String songId = "01";
        String playlistId = "01";
        String host = "host";

        Song song = new Song();
        song.setSongArtist(songArtist);
        song.setSongTitle(songTitle);
        song.setSongId(songId);

        PlaylistEntry pe = new PlaylistEntry(song);

        List<PlaylistEntry> peList = new ArrayList<>();

        Playlist playlist = new Playlist();
        playlist.setPlaylistId(playlistId);
        playlist.setHost(host);
        playlist.setSongs(peList);


         when(playlistDao.getPlaylist(playlistId)).thenReturn(playlist);


        RemoveSongFromPlaylistRequest request = RemoveSongFromPlaylistRequest.builder()
                .withSongTitle(pe.getSongTitle())
                .withSongArtist(pe.getSongArtist())
                .withPlaylistId(playlist.getPlaylistId())
                .build();


        peList.remove(pe);
        playlist.setSongs(peList);


        when(playlistDao.savePlaylist(playlist)).thenReturn(playlist);
        RemoveSongFromPlaylistResult result = removeSongFromPlaylistActivity.handleRequest(request);

        verify(playlistDao).savePlaylist(any(Playlist.class));
        assertTrue(result.getSongList().isEmpty());
    }

}
