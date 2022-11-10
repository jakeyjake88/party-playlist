package com.nashss.se.partyplaylist.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.ArrayList;
import java.util.List;

public class AddGuestToPartyRequest {

    private final String userId;

    private final String firstName;

    private final String lastName;

    private Boolean isAdmin;

    private List<String> songsAdded;

    private List<String> songsUpvoted;


    private AddGuestToPartyRequest(String userId, String firstName, String lastName, Boolean isAdmin) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isAdmin = isAdmin;
        this.songsAdded = new ArrayList<>();
        this.songsUpvoted  = new ArrayList<>();
    }


    public String getUserId() { return userId; }

    public String getFirstName() { return firstName; }

    public String getLastName() { return lastName; }

    public Boolean isAdmin() { return isAdmin; }

    public List<String> getSongsAdded() { return songsAdded; }

    public List<String> getSongsUpvoted() { return songsUpvoted; }

    @Override
    public String toString() {
        return "AddGuestToPartyRequest{" +
                "userId='" + userId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", isAdmin='" + isAdmin +
                '}';
    }

    public static Builder builder() { return new Builder(); }

    @JsonPOJOBuilder
    public static class Builder {

        private String userId;

        private String firstName;

        private String lastName;

        private Boolean isHost;

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder withIsAdmin(Boolean isAdmin) {
            this.isHost = isAdmin;
            return this;
        }

        public AddGuestToPartyRequest build() {
            return new AddGuestToPartyRequest(userId, firstName, lastName, isHost);
        }


    }
}




