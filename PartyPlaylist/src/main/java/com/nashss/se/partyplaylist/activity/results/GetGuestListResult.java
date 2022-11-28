package com.nashss.se.partyplaylist.activity.results;

import java.util.Set;

public class GetGuestListResult {

    private final Set<String> guestList;

    private GetGuestListResult(Set<String> guestList) {
        this.guestList = guestList;
    }

    public Set<String> getGuestList() {
        return guestList;
    }

    @Override
    public String toString() {
        return "GetGuestListResult{" +
                "guestList=" + guestList +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Set<String> guestList;

        public Builder withGuestList(Set<String> guestList) {
            this.guestList = guestList;
            return this;
        }

        public GetGuestListResult build() {
            return new GetGuestListResult(guestList);
        }
    }
}
