package com.nashss.se.partyplaylist.activity.results;

import com.nashss.se.partyplaylist.models.UserModel;

public class AddGuestToPartyResult {

    private final UserModel guest;

    private AddGuestToPartyResult(UserModel guest) {
        this.guest = guest;
    }

    public UserModel getGuest() {
        return guest;
    }

    @Override
    public String toString() {
        return "AddGuestToPartyResult{" +
                "guest=" + guest +
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
        private UserModel userModel;

        /**
         * Builder to create AddGuestToPartyResult object.
         * @param userModel the list of users at the party.
         * @return the Builder object
         */
        public Builder withUserModel(UserModel userModel) {
            this.userModel = userModel;
            return this;
        }

        /**
         * Builds the AddGuestToPartyResult object.
         * @return new AddGuestToPartyResult
         */
        public AddGuestToPartyResult build() {
            return new AddGuestToPartyResult(userModel);
        }
    }


}
