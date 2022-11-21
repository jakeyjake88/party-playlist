package com.nashss.se.partyplaylist.activity.results;

import com.nashss.se.partyplaylist.models.PlaylistModel;

public class RemoveSongFromPlaylistResult {

    private final PlaylistModel playlist;

    private RemoveSongFromPlaylistResult(PlaylistModel playlistModel) {
        this.playlist = playlistModel;
    }

    @Override
    public String toString() {
        return "RemoveSongFromPlaylistResult{" +
                "playlistModel" + playlist +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private PlaylistModel playlist;

        public Builder withPlaylist(PlaylistModel playlist) {
            this.playlist = playlist;
            return this;
        }

        public RemoveSongFromPlaylistResult build() { return new RemoveSongFromPlaylistResult(playlist); }
    }
}
