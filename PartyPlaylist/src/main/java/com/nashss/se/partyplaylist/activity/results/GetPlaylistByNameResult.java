package com.nashss.se.partyplaylist.activity.results;

import com.nashss.se.partyplaylist.models.PlaylistModel;

public class GetPlaylistByNameResult {

    private final PlaylistModel playlist;

    private GetPlaylistByNameResult(PlaylistModel playlist) {
        this.playlist = playlist;
    }

    public PlaylistModel getPlaylist() {
        return playlist;
    }

    @Override
    public String toString() {
        return "GetPlaylistByNameResult{" + "playlist=" + playlist + '}';
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

       public GetPlaylistByNameResult build() {
            return new GetPlaylistByNameResult(playlist);
       }
    }
}
