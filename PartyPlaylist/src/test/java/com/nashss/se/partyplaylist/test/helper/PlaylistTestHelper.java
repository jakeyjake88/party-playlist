package com.nashss.se.partyplaylist.test.helper;

import com.nashss.se.partyplaylist.dynamodb.models.Playlist;
import com.nashss.se.partyplaylist.dynamodb.models.PlaylistEntry;
import com.nashss.se.partyplaylist.dynamodb.models.Song;

import java.util.List;

public final class PlaylistTestHelper {
    private PlaylistTestHelper() {}

    private static Song song = new Song();

    public static Playlist generatePlaylist() {
        song.setSongId("01");
        song.setSongArtist("artist");
        song.setSongTitle("title");
        Playlist playlist = new Playlist();
        playlist.setPlaylistId("playlistId");
        playlist.setPlaylistName("playlist");
        playlist.setSongs(List.of(new PlaylistEntry(song)));

        return playlist;
    }
}
