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

    private CreatePlaylistRequest(String playlistName) {
        this.playlistId = "01";
        this.playlistName = playlistName;
    }
    public String getPlaylistId() {
        return playlistId;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    @Override
    public String toString() {
        return "CreatePlaylistRequest{" +
                "playlistId='" + playlistId + '\'' +
                ", name='" + playlistName + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String playlistName;

        public Builder withPlaylistName(String playlistName) {
            this.playlistName = playlistName;
            return this;
        }

        public CreatePlaylistRequest build() {
            return new CreatePlaylistRequest(playlistName);
        }
    }
}
