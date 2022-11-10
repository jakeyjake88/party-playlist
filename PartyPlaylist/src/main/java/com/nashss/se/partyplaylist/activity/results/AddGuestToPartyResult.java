package com.nashss.se.partyplaylist.activity.results;

import com.amazonaws.internal.config.Builder;

import java.util.ArrayList;

public class AddGuestToPartyResult {

private final List<UserModel> guestList;

private AddGuestToPartyResult(List<UserModel> guestList) { this.guestList = guestList; }

public List<UserModel> getGuestList() {
    return new ArrayList<>(guestList);
}

@Override
public String toString() {
    return "AddGuestToPartyResult{" +
            "guestList=" + guestList +
            '}';
}

public static Builder builder() { return new Builder(); }

public static class Builder {
    private List<UserModel> guestList;

    public Builder withGuestList(List<UserModel> guestList) {
        this.guestList = new ArrayList<>(guestList);
        return this;
    }


    public AddGuestToPartyResult build() { return new AddGuestToPartyResult(guestList); }
}


}
