package com.nashss.se.partyplaylist.test.helper;

import com.nashss.se.partyplaylist.dynamodb.models.Playlist;
import com.nashss.se.partyplaylist.dynamodb.models.PlaylistEntry;

import java.util.List;

public final class PlaylistTestHelper {
    private PlaylistTestHelper() {}

    public static Playlist generatePlaylist() {
        Playlist playlist = new Playlist();
        playlist.setPlaylistId("playlistId");
        playlist.setPlaylistName("playlist");
        playlist.setSongs(List.of(new PlaylistEntry(), new PlaylistEntry()));

        return playlist;
    }
}
