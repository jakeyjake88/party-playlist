package com.nashss.se.partyplaylist.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = AddSongToPlaylistRequest.Builder.class)
public class AddSongToPlaylistRequest {
    private final String playlistId;
    private final String songTitle;
    private final String songArtist;

    private AddSongToPlaylistRequest(String playlistId, String songTitle, String songArtist) {
        this.playlistId = playlistId;
        this.songTitle = songTitle;
        this.songArtist = songArtist;
    }

    public String getPlaylistId() {
        return playlistId;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public String getSongArtist() {
        return songArtist;
    }

    @Override
    public String toString() {
        return "AddSongToPlaylistRequest{" +
                "playlistId='" + playlistId + '\'' +
                ", songTitle='" + songTitle + '\'' +
                ", songArtist='" + songArtist + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String playlistId;
        private String songTitle;
        private String songArtist;

        public Builder withPlaylistId(String playlistId) {
            this.playlistId = playlistId;
            return this;
        }

        public Builder withSongTitle(String songTitle) {
            this.songTitle = songTitle;
            return this;
        }

        public Builder withSongArtist(String songArtist) {
            this.songArtist = songArtist;
            return this;
        }

        public AddSongToPlaylistRequest build() {
            return new AddSongToPlaylistRequest(playlistId, songTitle, songArtist);
        }
    }
}
