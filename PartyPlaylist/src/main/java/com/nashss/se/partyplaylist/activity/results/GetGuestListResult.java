package com.nashss.se.partyplaylist.activity.results;

import com.nashss.se.partyplaylist.models.UserModel;

import java.util.List;

public class GetGuestListResult {

    private final List<UserModel> guestList;

    GetGuestListResult(List<UserModel> guestList) {
        this.guestList = guestList;
    }

    List<UserModel> getGuestList() {
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
        private List<UserModel> guestList;

        public Builder withGuestList(List<UserModel> guestList) {
            this.guestList = guestList;
            return this;
        }

        public GetGuestListResult build() {
            return new GetGuestListResult(guestList);
        }
    }
}
