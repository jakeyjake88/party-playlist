package com.nashss.se.partyplaylist.activity.results;

import com.nashss.se.partyplaylist.models.PlaylistEntryModel;

import java.util.List;


public class RemoveSongFromPlaylistResult {

    private final List<PlaylistEntryModel> songList;

    private RemoveSongFromPlaylistResult(List<PlaylistEntryModel> songList) {
        this.songList = songList;
    }

    public List<PlaylistEntryModel> getSongList() {
        return songList;
    }

    @Override
    public String toString() {
        return "RemoveSongFromPlaylistResult{" +
                "songList=" + songList +
                '}';
    }


    //CHECKSTYLE:OFF:Builder
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private List<PlaylistEntryModel> songList;

        public Builder withPlaylist(List<PlaylistEntryModel> songList) {
            this.songList = songList;
            return this;
        }

        public RemoveSongFromPlaylistResult build() { return new RemoveSongFromPlaylistResult(songList); }
    }
}
