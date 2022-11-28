package com.nashss.se.partyplaylist.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = AddUpvoteToSongRequest.Builder.class)
public class AddUpvoteToSongRequest {
    private final String playlistId;
    private final String songTitle;
    private final String songArtist;

    private AddUpvoteToSongRequest(String playlistId, String songTitle, String songArtist) {
        this.playlistId = playlistId;
        this.songTitle = songTitle;
        this.songArtist = songArtist;
    }

    public String getPlaylistId() { return playlistId; }

    public String getSongTitle() { return songTitle; }

    public String getSongArtist() { return songArtist; }

    @Override
    public String toString() {
        return "AddUpvoteToSongRequest{" +
                "playlistId='" + playlistId +'\'' +
                ", songTitle='" + songTitle + '\'' +
                ", songArtist='" + songArtist + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() { return new Builder(); }

    @JsonPOJOBuilder
    public static class Builder {
        private String playlistId;
        private String songTitle;
        private String songArtist;

        public AddUpvoteToSongRequest.Builder withPlaylistId(String playlistId) {
            this.playlistId = playlistId;
            return this;
        }

        public AddUpvoteToSongRequest.Builder withSongTitle(String songTitle) {
            this.songTitle = songTitle;
            return this;
        }

        public AddUpvoteToSongRequest.Builder withSongArtist(String songArtist) {
            this.songArtist = songArtist;
            return this;
        }

        public AddUpvoteToSongRequest build() {
            return new AddUpvoteToSongRequest(playlistId, songTitle, songArtist);
        }
    }
}
