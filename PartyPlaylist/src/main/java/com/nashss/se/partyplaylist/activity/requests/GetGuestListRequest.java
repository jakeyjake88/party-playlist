package com.nashss.se.partyplaylist.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = GetGuestListRequest.Builder.class)
public class GetGuestListRequest {
    private final String playlistId;


    private GetGuestListRequest(String playlistId) {
        this.playlistId = playlistId;
    }

    public String getPlaylistId() {
        return playlistId;
    }

    @Override
    public String toString() {
        return "GetGuestListRequest{" +
                "playlistId='" + playlistId + '\'' +
                '}';
    }

    /**
     * Creates a new builder for GetGuestListRequest.
     *
     * @return Builder
     */

    //CHECKSTYLE:OFF:Builder
    public static AddGuestToPartyRequest.Builder builder() {
        return new AddGuestToPartyRequest.Builder();
    }

    /**
     * Builder for AddGuestToPartyRequest.
     */
    @JsonPOJOBuilder
    public static class Builder {

        private String playlistId;


        /**
         * With playlistId parameter for GetGuestList builder.
         * @param playlistId with the playlistId
         * @return this
         */
        public Builder withPlaylistId(String playlistId) {
            this.playlistId = playlistId;
            return this;
        }

        /**
         * Build AddGuestToPartyRequest.
         * @return AddGuestToPartyRequest
         */
        public GetGuestListRequest build() {
            return new GetGuestListRequest(playlistId);
        }


    }
}
