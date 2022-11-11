package com.nashss.se.partyplaylist.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = CreatePlaylistRequest.Builder.class)
public class CreatePlaylistRequest {

    private final String playlistId;
    private final String name;

    private CreatePlaylistRequest(String playlistId, String name) {
        this.playlistId = playlistId;
        this.name = name;
    }
    public String getPlaylistId() {
        return playlistId;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "CreatePlaylistRequest{" +
                "playlistId='" + playlistId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {

        private String playlistId;
        private String name;

        public Builder withPlaylistId(String playlistId) {
            this.playlistId = playlistId;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public CreatePlaylistRequest build() {
            return new CreatePlaylistRequest(playlistId, name);
        }
    }
}
