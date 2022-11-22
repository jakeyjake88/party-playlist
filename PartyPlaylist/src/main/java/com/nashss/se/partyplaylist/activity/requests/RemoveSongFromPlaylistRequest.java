package com.nashss.se.partyplaylist.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = RemoveSongFromPlaylistRequest.Builder.class)
public class RemoveSongFromPlaylistRequest {
    private final String songTitle;
    private final String songArtist;
    private final String playlistId;

    private RemoveSongFromPlaylistRequest(String songTitle, String songArtist, String playlistId) {
        this.songTitle = songTitle;
        this.songArtist = songArtist;
        this.playlistId = playlistId;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public String getSongArtist() {
        return songArtist;
    }

    public String getPlaylistId() {
        return playlistId; 
    }


    @Override
    public String toString() {
        return "AddSongToPlaylistRequest{" +
                ", songTitle='" + songTitle + '\'' +
                ", playlistId='" + playlistId + '\'' +
                ", songArtist='" + songArtist + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String songTitle;
        private String songArtist;
        private String playlistId;

        public Builder withSongTitle(String songTitle) {
            this.songTitle = songTitle;
            return this;
        }

        public Builder withSongArtist(String songArtist) {
            this.songArtist = songArtist;
            return this;
        }

        public Builder withPlaylistId(String playlistId) {
            this.playlistId = playlistId;
            return this;
        }

        public RemoveSongFromPlaylistRequest build() { return new RemoveSongFromPlaylistRequest(songTitle, songArtist, playlistId); }
    }
}
