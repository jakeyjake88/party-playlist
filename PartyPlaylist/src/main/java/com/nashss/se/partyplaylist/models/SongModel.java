package com.nashss.se.partyplaylist.models;

import java.util.Objects;

public class SongModel {

    private final String songId;
    private final String songTitle;
    private final String songArtist;
    private final String genre;
    private final Integer songLength;

    private SongModel(String songId, String songTitle, String songArtist, String genre, Integer songLength) {
        this.songId = songId;
        this.songTitle = songTitle;
        this.songArtist = songArtist;
        this.genre = genre;
        this.songLength = songLength;
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

    public String getGenre() {
        return genre;
    }

    public Integer getSongLength() {
        return songLength;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SongModel songModel = (SongModel) o;
        return Objects.equals(songId, songModel.songId) && Objects.equals(songTitle, songModel.songTitle) && Objects.equals(songArtist, songModel.songArtist) && Objects.equals(genre, songModel.genre) && Objects.equals(songLength, songModel.songLength);
    }

    @Override
    public int hashCode() {
        return Objects.hash(songId, songTitle, songArtist, genre, songLength);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String songId;
        private String songTitle;
        private String songArtist;
        private String genre;
        private Integer songLength;

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

        public Builder withGenre(String genre) {
            this.genre = genre;
            return this;
        }

        public Builder withSongLength(Integer songLength) {
            this.songLength = songLength;
            return this;
        }

        public SongModel build() {
            return new SongModel(songId, songTitle, songArtist, genre, songLength);
        }

    }
}

