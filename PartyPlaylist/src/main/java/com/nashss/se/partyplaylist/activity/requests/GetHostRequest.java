package com.nashss.se.partyplaylist.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = GetHostRequest.Builder.class)
public class GetHostRequest {
    private final String hostName;
    private final String playlistName;

    private GetHostRequest(String hostName, String playlistName) {
        this.hostName = hostName;
        this.playlistName = playlistName;
    }

    public String getHostName() {
        return hostName;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    @Override
    public String toString() {
        return "GetHostRequest{" +
                "hostName='" + hostName + '\'' +
                ", playlistName='" + playlistName + '\'' +
                '}';
    }

    /**
     * Creates a new builder for CreateHostRequest.
     *
     * @return Builder
     */

    //CHECKSTYLE:OFF:Builder
    public static GetHostRequest.Builder builder() {
        return new GetHostRequest.Builder();
    }

    /**
     * Builder for CreateHostRequest.
     */
    @JsonPOJOBuilder
    public static class Builder {
        private String hostName;
        private String playlistName;

        public Builder withHostName(String hostName) {
            this.hostName = hostName;
            return this;
        }

        public Builder withPlaylistName(String playlistName) {
            this.playlistName = playlistName;
            return this;
        }

        public GetHostRequest build() {
            return new GetHostRequest(hostName, playlistName);
        }

    }

}
