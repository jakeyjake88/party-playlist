//CHECKSTYLE:OFF:ALL
package com.nashss.se.partyplaylist.activity.results;

import com.nashss.se.partyplaylist.models.PlaylistModel;
public class CreatePlaylistResult {
    //CHECKSTYLE:OFF:Builder
    private final PlaylistModel playlist;

    public CreatePlaylistResult(PlaylistModel playlist) {
        this.playlist = playlist;
    }

    public PlaylistModel getPlaylist() {
        return playlist;
    }

    @Override
    public String toString() {
        return "CreatePlaylistResult{" +
                "playlist=" + playlist +
                '}';
    }


    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private PlaylistModel playlist;

        public Builder withPlaylist(PlaylistModel playlist) {
            this.playlist = playlist;
            return this;
        }

        public CreatePlaylistResult build() {
            return new CreatePlaylistResult(playlist);
        }
    }
}
