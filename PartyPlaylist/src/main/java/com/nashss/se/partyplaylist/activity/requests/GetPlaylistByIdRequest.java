package com.nashss.se.partyplaylist.activity.requests;

public class GetPlaylistByIdRequest {

    private final String playlistId;

    private GetPlaylistByIdRequest(String playlistId) {
        this.playlistId = playlistId;
    }

    public String getPlaylistId() {
        return playlistId;
    }

    @Override
    public String toString() {
        return "GetPlaylistByIdRequest{" +
                "playlistId='" + playlistId + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String playlistId;

        public Builder withPlaylistId(String playlistId) {
            this.playlistId = playlistId;
            return this;
        }

        public GetPlaylistByIdRequest build() {
            return new GetPlaylistByIdRequest(playlistId);
        }
    }
}
