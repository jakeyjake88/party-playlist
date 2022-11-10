package com.nashss.se.partyplaylist.activity.results;

import com.amazonaws.internal.config.Builder;
import com.nashss.se.partyplaylist.models.UserModel;

public class GetGuestResult {

    private final UserModel guest;

    private GetGuestResult(UserModel guest) { this.guest = guest; }

    public UserModel getGuest() { return guest; }

    @Override
    public String toString() {
        return "GetGuestResult(" +
                "guest=" + guest +
                '}';
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private UserModel guest;
        public Builder withGuest(UserModel guest) {
            this.guest = guest;
            return this;
        }

        public GetGuestResult build() { return new GetGuestResult(guest); }

    }

}
