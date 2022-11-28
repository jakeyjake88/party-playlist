package com.nashss.se.partyplaylist.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

/**
 * Implementation of the CreatePlaylistRequest for the PartyPlaylistService's CreatePlaylist API.
 * <p>
 * This API allows Creates the request for CreatePlaylistActivity.
 */
@JsonDeserialize(builder = CreatePlaylistRequest.Builder.class)
public class CreatePlaylistRequest {

    private final String playlistId;
    private final String playlistName;
    private final String host;

    private CreatePlaylistRequest(String playlistName, String host) {
        // TODO replace with actual value
        this.playlistId = "01";
        this.playlistName = playlistName;
        this.host = host;
    }
    public String getPlaylistId() {
        return playlistId;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public String getHost() {
        return host;
    }

    @Override
    public String toString() {
        return "CreatePlaylistRequest{" +
                "playlistId='" + playlistId + '\'' +
                ", name='" + playlistName + '\'' +
                ", host='" + host +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String playlistName;

        private String host;

        public Builder withPlaylistName(String playlistName) {
            this.playlistName = playlistName;
            return this;
        }

        public Builder withHost(String host) {
            this.host = host;
            return this;
        }

        public CreatePlaylistRequest build() {
            return new CreatePlaylistRequest(playlistName, host);
        }
    }
}
