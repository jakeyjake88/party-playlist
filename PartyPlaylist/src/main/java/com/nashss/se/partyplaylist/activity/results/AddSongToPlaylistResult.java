package com.nashss.se.partyplaylist.activity.results;

import com.nashss.se.partyplaylist.models.PlaylistEntryModel;

import java.util.ArrayList;
import java.util.List;

public class AddSongToPlaylistResult {

    private final List<PlaylistEntryModel> songList;

    private AddSongToPlaylistResult(List<PlaylistEntryModel> songList) {
        this.songList = songList;
    }

    public List<PlaylistEntryModel> getSongList() {
        return new ArrayList<>(songList);
    }

    @Override
    public String toString() {
        return "AddSongToPlaylistResult{" +
                "songList=" + songList +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<PlaylistEntryModel> songList;

        public Builder withSongList(List<PlaylistEntryModel> songList) {
            this.songList = new ArrayList<>(songList);
            return this;
        }

        public AddSongToPlaylistResult build() {
            return new AddSongToPlaylistResult(songList);
        }
    }
}
