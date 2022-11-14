package com.nashss.se.partyplaylist.test.helper;

import com.nashss.se.partyplaylist.dynamodb.models.Playlist;
import com.nashss.se.partyplaylist.dynamodb.models.PlaylistEntry;
import com.nashss.se.partyplaylist.models.SongModel;

import java.util.List;

public final class PlaylistTestHelper {
    private PlaylistTestHelper() {}

    private static SongModel songModel = SongModel.builder()
            .withSongId("01")
            .build();

    public static Playlist generatePlaylist() {
        Playlist playlist = new Playlist();
        playlist.setPlaylistId("playlistId");
        playlist.setPlaylistName("playlist");
        playlist.setSongs(List.of(new PlaylistEntry(songModel)));

        return playlist;
    }
}
