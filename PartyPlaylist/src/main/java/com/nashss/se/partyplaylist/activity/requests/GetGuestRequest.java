package com.nashss.se.partyplaylist.activity.requests;


/**
 * GetGuestRequest class to for the Get Guest Request.
 */
public class GetGuestRequest {

    private final String userId;

    private GetGuestRequest(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "GetGuestRequest{" +
                "userId='" + userId + '\'' +
                '}';
    }

    /**
     * Creates a new builder for a GetGuestRequest object.
     * @return Builder
     */

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String userId;

        /**
         *
         * @param userId the userId
         * @return this method
         */
        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        /**
         * GetGuestRequest Builder.
         * @return new GetGuestRequest
         */
        public GetGuestRequest build() {
            return new GetGuestRequest(userId);
        }
    }

}
