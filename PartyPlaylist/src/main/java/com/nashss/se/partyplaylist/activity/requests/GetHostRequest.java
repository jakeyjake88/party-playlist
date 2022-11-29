package com.nashss.se.partyplaylist.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = GetHostRequest.Builder.class)
public class GetHostRequest {
    private final String firstName;
    private final String lastName;
    private final String playlistName;

    private GetHostRequest(String firstName, String lastName, String playlistName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.playlistName = playlistName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    @Override
    public String toString() {
        return "GetHostRequest{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
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
        private String firstName;
        private String lastName;
        private String playlistId;

        public GetHostRequest.Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public GetHostRequest.Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public GetHostRequest.Builder withPlaylistId(String playlistId) {
            this.playlistId = playlistId;
            return this;
        }

        public GetHostRequest build() {
            return new GetHostRequest(firstName, lastName, playlistId);
        }

    }

}
