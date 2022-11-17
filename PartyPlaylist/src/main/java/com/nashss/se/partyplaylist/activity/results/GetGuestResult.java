package com.nashss.se.partyplaylist.activity.results;
import com.nashss.se.partyplaylist.models.UserModel;


public class GetGuestResult {

    private final UserModel guest;

    private GetGuestResult(UserModel guest) {
        this.guest = guest;
    }

    public UserModel getGuest() {
        return guest;
    }

    @Override
    public String toString() {
        return "GetGuestResult(" +
                "guest=" + guest +
                '}';
    }

    /**
     * Creates a new Builder Object.
     * @return builder object
     */
    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private UserModel guest;

        /**
         * Builds the GetGuestResult with guest.
         * @param guest at the party to pass in
         * @return builder object with guest
         */
        public Builder withGuest(UserModel guest) {
            this.guest = guest;
            return this;
        }

        /**
         * Builds the GetGuestResult object.
         * @return new GetGuestResult object
         */
        public GetGuestResult build() {
            return new GetGuestResult(guest);
        }

    }

}
