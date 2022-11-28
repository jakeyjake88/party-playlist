package com.nashss.se.partyplaylist.models;

import com.nashss.se.partyplaylist.dynamodb.models.PlaylistEntry;

import java.util.List;

import java.util.Objects;

public class PlaylistModel {

    private final String playlistId;
    private final String playlistName;
    private final List<PlaylistEntry> songs;

    private PlaylistModel(String playlistID, String playlistName, List<PlaylistEntry> songs) {
        this.playlistId = playlistID;
        this.playlistName = playlistName;
        this.songs = songs;
    }

    public String getPlaylistID() {
        return playlistId;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public List<PlaylistEntry> getSongs() {
        return songs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PlaylistModel that = (PlaylistModel) o;

        return Objects.equals(playlistId, that.playlistId) &&
                Objects.equals(playlistName, that.playlistName) &&
                Objects.equals(songs, that.songs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playlistId, playlistName, songs);
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String playlistId;
        private String playlistName;
        private List<PlaylistEntry> songs;

        public Builder withPlaylistId(String playlistId) {
            this.playlistId = playlistId;
            return this;
        }

        public Builder withPlaylistName(String playlistName) {
            this.playlistName = playlistName;
            return this;
        }

        public Builder withSongs(List<PlaylistEntry> songs) {
            this.songs = songs;
            return this;
        }

        public PlaylistModel build() {
            return new PlaylistModel(playlistId, playlistName, songs);
        }
    }
}
