package com.nashss.se.partyplaylist.activity.requests;

public class GetPlaylistByNameRequest {

    private final String playlistName;

    private GetPlaylistByNameRequest(String playlistName) {
        this.playlistName = playlistName;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    @Override
    public String toString() {
        return "GetPlaylistByNameRequest{" +
                "playlistName='" + playlistName + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String playlistName;

        public Builder withPlaylistName(String playlistName) {
            this.playlistName = playlistName;
            return this;
        }

        public GetPlaylistByNameRequest build() {
            return new GetPlaylistByNameRequest(playlistName);
        }
    }
}
