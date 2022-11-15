package com.nashss.se.partyplaylist.activity.results;

import com.nashss.se.partyplaylist.models.UserModel;

import java.util.ArrayList;
import java.util.List;

public class AddGuestToPartyResult {

    private final List<UserModel> guestList;

    private AddGuestToPartyResult(List<UserModel> guestList) {
        this.guestList = guestList;
    }

    public List<UserModel> getGuestList() {

        return new ArrayList<>(guestList);
    }

    @Override
    public String toString() {
        return "AddGuestToPartyResult{" +
                "guestList=" + guestList +
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
        private List<UserModel> guestList;

        /**
         * Builder to create AddGuestToPartyResult object.
         * @param guestList the list of users at the party.
         * @return the Builder object
         */
        public Builder withGuestList(List<UserModel> guestList) {
            this.guestList = new ArrayList<>(guestList);
            return this;
        }

        /**
         * Builds the AddGuestToPartyResult object.
         * @return new AddGuestToPartyResult
         */
        public AddGuestToPartyResult build() {
            return new AddGuestToPartyResult(guestList);
        }
    }


}
