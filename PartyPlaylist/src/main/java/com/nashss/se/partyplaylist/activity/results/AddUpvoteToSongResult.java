package com.nashss.se.partyplaylist.activity.results;

import com.nashss.se.partyplaylist.activity.requests.AddUpvoteToSongRequest;
import com.nashss.se.partyplaylist.dynamodb.models.PlaylistEntry;

public class AddUpvoteToSongResult {
    private final PlaylistEntry song;

    private AddUpvoteToSongResult(PlaylistEntry song) {
        this.song = song;
    }

    public PlaylistEntry getSong() { return song; }

    @Override
    public String toString() {
        return "AddSongToPlaylistResult{" +
                "song=" + song +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private PlaylistEntry song;

        public Builder withSong(PlaylistEntry song) {
            this.song = song;
            return this;
        }

        public AddUpvoteToSongResult build() { return new AddUpvoteToSongResult(song); }
    }
}
