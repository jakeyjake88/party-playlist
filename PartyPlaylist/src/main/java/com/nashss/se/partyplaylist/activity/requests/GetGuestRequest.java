package com.nashss.se.partyplaylist.activity.requests;

import com.amazonaws.internal.config.Builder;

public class GetGuestRequest {

    private final String userId;

    private GetGuestRequest(String userId) { this.userId = userId; }

    public String getUserId() { return userId; }

    @Override
    public String toString() {
        return "GetGuestRequest{" +
                "userId='" + userId + '\'' +
                '}';
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {

        private String userId;

        public Builder withUserId(String UserId) {
            this.userId = userId;
            return this;
        }

        public GetGuestRequest build() { return new GetGuestRequest(userId); }
    }

}
