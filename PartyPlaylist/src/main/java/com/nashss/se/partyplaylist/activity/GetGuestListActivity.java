package com.nashss.se.partyplaylist.activity;

import com.nashss.se.partyplaylist.activity.requests.GetGuestRequest;
import com.nashss.se.partyplaylist.activity.results.GetGuestResult;
import com.nashss.se.partyplaylist.converters.ModelConverter;
import com.nashss.se.partyplaylist.dynamodb.PlaylistDao;
import com.nashss.se.partyplaylist.dynamodb.UserDAO;
import com.nashss.se.partyplaylist.dynamodb.models.User;
import com.nashss.se.partyplaylist.models.UserModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class GetGuestListActivity {

    private final Logger log = LogManager.getLogger();

    private final PlaylistDao playlistDao;

    /**
     * Instantiates a new GetGuestListActivity object.
     *
     * @param playlistDao to access the playlist table.
     */

    @Inject
    public GetGuestListActivity(PlaylistDao playlistDao) {
        this.playlistDao = playlistDao;
    }

    //CHECKSTYLE:OFF:LeftCurly
    /**
     * This method handles the incoming request by retrieving the guest list from the database.
     * <p>
     * It then returns the guest list.
     * <p>
     *
     * @param getGuestListRequest request object containing the playlistId
     * @return getGuestListResult result object containing the API defined {@link List<UserModel> }
     */

    public GetGuestListResult handleRequest(final GetGuestRequest getGuestRequest) {
        log.info("Received GetGuestRequest {}", getGuestRequest);

        String requestedId = getGuestRequest.getUserId();
        User guest = userDAO.getGuest(requestedId);
        UserModel userModel = new ModelConverter().toUserModel(guest);
        return GetGuestResult.builder().withGuest(userModel).build();
    }

}
