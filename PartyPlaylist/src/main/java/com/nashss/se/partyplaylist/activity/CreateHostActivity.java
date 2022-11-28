package com.nashss.se.partyplaylist.activity;

import com.nashss.se.partyplaylist.activity.requests.CreateHostRequest;
import com.nashss.se.partyplaylist.activity.results.CreateHostResult;
import com.nashss.se.partyplaylist.converters.ModelConverter;
import com.nashss.se.partyplaylist.dynamodb.UserDAO;
import com.nashss.se.partyplaylist.dynamodb.models.User;
import com.nashss.se.partyplaylist.models.UserModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

/**
 * Implementation of the CreateHostActivity for the PartyPlaylist CreateHost API.
 *
 * This API creates the host for the party.
 */
public class CreateHostActivity {

    private final Logger log = LogManager.getLogger();
    private final UserDAO userDAO;

    /**
     * CreateHostActivity Constructor.
     *
     * @param userDAO to initialize the userDAO;
     */

    @Inject
    public CreateHostActivity(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    /**
     * This method handles the incoming request by creating a host for the party
     * <p>
     * It then returns the guestList.
     *
     * @param createHostRequest request object containing the user ID
     *                                 to retrieve the user data
     * @return addGuestToPartyResult result object containing the party's updated list of
     *                                 API defined {@link UserModel}s
     */

    public CreateHostResult handleRequest(final CreateHostRequest createHostRequest) {
        log.info("Received CreateHostRequest {}", createHostRequest);

        User host = new User();
        host.setFirstName(createHostRequest.getFirstName());
        host.setLastName(createHostRequest.getLastName());
        host.setIsHost(createHostRequest.isAdmin());
        host.setSongsAdded(createHostRequest.getSongsAdded());
        host.setSongsUpvoted(createHostRequest.getSongsUpvoted());

        userDAO.addGuestToParty(host);
        log.info("Created a host, and started the guest list");

        UserModel userModel = new ModelConverter().toUserModel(host);

        return CreateHostResult.builder()
                .withUserModel(userModel)
                .build();
    }
}
