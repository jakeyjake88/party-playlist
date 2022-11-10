package com.nashss.se.partyplaylist.activity;

import com.nashss.se.partyplaylist.activity.requests.AddGuestToPartyRequest;
import com.nashss.se.partyplaylist.activity.results.AddGuestToPartyResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/**
 * Implementation of the AddGuestToPartyActivity for the PartyPlaylist AddGuestToParty API.
 *
 * This API allows the host to add a guest to their existing party.
 */
public class AddGuestToPartyActivity {

    private final Logger log = LogManager.getLogger();
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
        log.info("Received AddGuestToPartyRequest {} ", addGuestToPartyRequest);

        String userId = addGuestToPartyRequest.getUserId();
        User guestToAdd = userDao.getGuest(userId);

        UserList guestList = userDao.getGuestList(addGuestToPartyRequest.getUserId());

        List<UserModel> userModels = new ModelConverter().toUserModelList(guestList.getGuestList());

        return AddGuestToPartyResult.builder()
                .withGuestList(userModels)
                .build();

    }
}
