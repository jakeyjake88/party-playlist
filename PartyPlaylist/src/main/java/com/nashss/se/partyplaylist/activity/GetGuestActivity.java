package com.nashss.se.partyplaylist.activity;
import com.nashss.se.partyplaylist.activity.requests.GetGuestRequest;
import com.nashss.se.partyplaylist.activity.results.GetGuestResult;
import com.nashss.se.partyplaylist.converters.ModelConverter;
import com.nashss.se.partyplaylist.dynamodb.UserDAO;
import com.nashss.se.partyplaylist.dynamodb.models.User;
import com.nashss.se.partyplaylist.models.UserModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

/**
 * Implementation of the GetGuestActivity for the PartyPlaylist GetGuest API.
 *
 * This API allows the host to get one of the guests at the party.
 */
public class GetGuestActivity {

    private final Logger log = LogManager.getLogger();

    private final UserDAO userDAO;

    /**
     * Instantiates a new GetGuestActivity object.
     *
     * @param userDAO to access the user table.
     */

    @Inject
    public GetGuestActivity(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    //CHECKSTYLE:OFF:LeftCurly
    /**
     * This method handles the incoming request by retrieving the guest from the database.
     * <p>
     * It then returns the guest.
     * <p>
     * If the guest does not exist, this should throw a UserNotFoundException.
     *
     * @param getGuestRequest request object containing the userId
     * @return getGuestResult result object containing the API defined {@link UserModel }
     */

    public GetGuestResult handleRequest(final GetGuestRequest getGuestRequest) {
        log.info("Received GetGuestRequest {}", getGuestRequest);

        String requestedId = getGuestRequest.getUserId();
        User guest = userDAO.getGuest(requestedId);
        UserModel userModel = new ModelConverter().toUserModel(guest);
        return GetGuestResult.builder().withGuest(userModel).build();
    }

}
