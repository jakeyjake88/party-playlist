package com.nashss.se.partyplaylist.activity.results;

import com.nashss.se.partyplaylist.dynamodb.models.Song;

public class RemoveSongFromPlaylistResult {

    private final Song song;

    private RemoveSongFromPlaylistResult(Song song) { this.song = song; }

    @Override
    public String toString() {
        return "RemoveSongFromPlaylistResult{" +
                "song" + song +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Song song;

        public Builder withSong(Song song) {
            this.song = new Song();
            return this;
        }

        public RemoveSongFromPlaylistResult build() { return new RemoveSongFromPlaylistResult(song); }
    }
}
