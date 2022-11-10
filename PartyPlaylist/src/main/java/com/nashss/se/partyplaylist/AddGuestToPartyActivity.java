package com.nashss.se.partyplaylist;

import com.nashss.se.partyplaylist.requests.AddGuestToPartyRequest;
import com.nashss.se.partyplaylist.results.AddGuestToPartyResult;

/**
 * Implementation of the AddGuestToPartyActivity for the PartyPlaylist AddGuestToParty API.
 *
 * This API allows the host to add a guest to their existing party.
 */
public class AddGuestToPartyActivity {

    private final UserDao userDao;

    /**
     * Instantiates a new AddGuestToPartyActivity object
     * @param userDAO UserDao to access the user table
     */
    @Inject
    public AddGuestToPartyActivity(UserDao userDao) {
        this.userDao = userDao;
    }

    public AddGuestToPartyResult handleRequest(final AddGuestToPartyRequest addGuestToPartyRequest) {

        User partyGuest = userDao.getUser(addGuestToPartyRequest.getUserName());

        List<UserModel> userModels = new ModelConverter().toUserModelList(userlist.getUserList());

        return AddGuestToPartyResult.builder()
                .withUserList(userModels)
                .build();

    }
}
