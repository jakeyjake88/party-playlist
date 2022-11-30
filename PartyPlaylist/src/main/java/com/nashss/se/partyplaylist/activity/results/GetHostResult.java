package com.nashss.se.partyplaylist.activity.results;

public class GetHostResult {
    private final String playlistId;

    private GetHostResult(String playlistId) {
        this.playlistId = playlistId;
    }

    public String getPlaylistId() {
        return playlistId;
    }

    @Override
    public String toString() {
        return "GetHostResult{" +
                "playlistId='" + playlistId + '\'' +
                '}';
    }

    /**
     * Creates a new Builder object.
     * @return new builder object
     */
    public static Builder builder() {
        return new Builder();
    }

    //CHECKSTYLE:OFF:Builder

    public static class Builder {
        private String playlistId;

        public Builder withPlaylistId(String playlistId) {
            this.playlistId = playlistId;
            return this;
        }

        public GetHostResult build() {
            return new GetHostResult(playlistId);
        }
    }

}
