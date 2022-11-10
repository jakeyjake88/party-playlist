package com.nashss.se.partyplaylist.activity;

import com.nashss.se.partyplaylist.activity.requests.AddGuestToPartyRequest;
import com.nashss.se.partyplaylist.activity.results.AddGuestToPartyResult;
import com.nashss.se.partyplaylist.dynamodb.UserDAO;
import com.nashss.se.partyplaylist.dynamodb.models.User;
import com.nashss.se.partyplaylist.exceptions.UserNotFoundException;
import com.nashss.se.partyplaylist.models.UserModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;


/**
 * Implementation of the AddGuestToPartyActivity for the PartyPlaylist AddGuestToParty API.
 *
 * This API allows the host to add a guest to their existing party.
 */
public class AddGuestToPartyActivity {

    private final Logger log = LogManager.getLogger();
    private final UserDAO userDAO;

    /**
     * Instantiates a new AddGuestToPartyActivity object
     * @param userDAO UserDao to access the user table
     */

    @Inject
    public AddGuestToPartyActivity(UserDAO userDao) {
        this.userDAO = userDao;
    }

    public AddGuestToPartyResult handleRequest(final AddGuestToPartyRequest addGuestToPartyRequest) {
        log.info("Received AddGuestToPartyRequest {} ", addGuestToPartyRequest);

        String userId = addGuestToPartyRequest.getUserId();
        User guestToAdd = userDAO.getUser(userId);

        List<User> guestList = userDAO.getGuestList(addGuestToPartyRequest.getUserId());

        List<UserModel> userModels = new ModelConverter().toUserModelList(guestList.getGuestList());

        return AddGuestToPartyResult.builder()
                .withGuestList(userModels)
                .build();

    }
}
