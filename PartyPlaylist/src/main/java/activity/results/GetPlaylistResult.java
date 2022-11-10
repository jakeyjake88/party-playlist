package activity.results;

import java.util.Locale;

public class GetPlaylistResult {

    private final PlaylistModel playlist;

    private GetPlaylistResult(PlaylistModel playlist) {
        this.playlist = playlist;
    }

    @Override
    public String toString() {
        return "GetPlaylistResult{" + "playlist=" + playlist + '}';
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

       public GetPlaylistResult build() {
            return new GetPlaylistResult(playlist);
       }
    }
}
