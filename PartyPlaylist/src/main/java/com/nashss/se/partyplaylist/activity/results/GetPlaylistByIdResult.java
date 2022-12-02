package com.nashss.se.partyplaylist.activity.results;

import com.nashss.se.partyplaylist.models.PlaylistModel;

public class GetPlaylistByIdResult {

    private final PlaylistModel playlist;

    private GetPlaylistByIdResult(PlaylistModel playlist) {
        this.playlist = playlist;
    }

    public PlaylistModel getPlaylist() {
        return playlist;
    }

    @Override
    public String toString() {
        return "GetPlaylistByIdResult{" + "playlist=" + playlist + '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private PlaylistModel playlist;

        public Builder withPlaylist(PlaylistModel playlist) {
            this.playlist = playlist;
            return this;
        }

        public GetPlaylistByIdResult build() {
            return new GetPlaylistByIdResult(playlist);
        }
    }
}
