package com.nashss.se.partyplaylist.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = AddSongToPlaylistRequest.Builder.class)
public class AddSongToPlaylistRequest {
    private final String songId;
    private final String songTitle;
    private final String songArtist;
    private final Integer songLength;

    private final String genre;

    private AddSongToPlaylistRequest(String songId, String songTitle, String songArtist, Integer songLength, String genre) {
        this.songId = songId;
        this.songTitle = songTitle;
        this.songArtist = songArtist;
        this.songLength = songLength;
        this.genre = genre;
    }

    public String getSongId() {
        return songId;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public String getSongArtist() {
        return songArtist;
    }

    public Integer getSongLength() {return songLength; }

    public String getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return "AddSongToPlaylistRequest{" +
                "songId='" + songId + '\'' +
                ", songTitle='" + songTitle + '\'' +
                ", songArtist='" + songArtist + '\'' +
                ", songLength=" + songLength +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String songId;
        private String songTitle;
        private String songArtist;
        private Integer songLength;

        private String genre;

        public Builder withSongId(String songId) {
            this.songId = songId;
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

        public Builder withSongLength(Integer songLength) {
            this.songLength = songLength;
            return this;
        }

        public Builder withGenre(String genre) {
            this.genre = genre;
            return this;
        }

        public AddSongToPlaylistRequest build() {
            return new AddSongToPlaylistRequest(songId, songTitle, songArtist, songLength, genre);
        }
    }
}
