package com.nashss.se.partyplaylist.models;

import com.nashss.se.partyplaylist.dynamodb.models.PlaylistEntry;

import java.util.Objects;

public class PlaylistEntryModel {
    private final String songId;
    private final String songTitle;
    private final String songArtist;
    private final String genre;
    private final Integer songLength;
    private final Boolean hasPlayed;
    private final Integer upvotes;

    private PlaylistEntryModel(String songId, String songTitle, String songArtist, String genre, Integer songLength, Boolean hasPlayed, Integer upvotes) {
        this.songId = songId;
        this.songTitle = songTitle;
        this.songArtist = songArtist;
        this.genre = genre;
        this.songLength = songLength;
        this.hasPlayed = hasPlayed;
        this.upvotes = upvotes;
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

    public Boolean getHasPlayed() {
        return hasPlayed;
    }

    public Integer getUpvotes() {
        return upvotes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlaylistEntryModel playlistEntryModel = (PlaylistEntryModel) o;
        return Objects.equals(songId, playlistEntryModel.songId) && Objects.equals(songTitle, playlistEntryModel.songTitle) && Objects.equals(songArtist, playlistEntryModel.songArtist) && Objects.equals(genre, playlistEntryModel.genre) && Objects.equals(songLength, playlistEntryModel.songLength);
    }

    @Override
    public int hashCode() {
        return Objects.hash(songId, songTitle, songArtist, genre, songLength);
    }

    public static PlaylistEntryModel.Builder builder() {
        return new PlaylistEntryModel.Builder();
    }

    public static class Builder {

        private String songId;
        private String songTitle;
        private String songArtist;
        private String genre;
        private Integer songLength;
        private Boolean hasPlayed;
        private Integer upvotes;

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

        public Builder withHasPlayed(Boolean hasPlayed) {
            this.hasPlayed = hasPlayed;
            return this;
        }

        public Builder withUpvotes(Integer upvotes) {
            this.upvotes = upvotes;
            return this;
        }

        public PlaylistEntryModel build() {
            return new PlaylistEntryModel(songId, songTitle, songArtist, genre, songLength, hasPlayed, upvotes);
        }

    }

}
