package com.nashss.se.partyplaylist.activity;
import com.nashss.se.partyplaylist.activity.requests.AddGuestToPartyRequest;
import com.nashss.se.partyplaylist.activity.results.AddGuestToPartyResult;
import com.nashss.se.partyplaylist.converters.ModelConverter;
import com.nashss.se.partyplaylist.dynamodb.PlaylistDao;
import com.nashss.se.partyplaylist.dynamodb.UserDAO;
import com.nashss.se.partyplaylist.dynamodb.models.Playlist;
import com.nashss.se.partyplaylist.dynamodb.models.User;
import com.nashss.se.partyplaylist.models.UserModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import javax.inject.Inject;

/**
 * Implementation of the AddGuestToPartyActivity for the PartyPlaylist AddGuestToParty API.
 *
 * This API allows the host to add a guest to their existing party.
 */
public class AddGuestToPartyActivity {

    private final Logger log = LogManager.getLogger();
    private final UserDAO userDAO;
    private final PlaylistDao playlistDao;

    /**
     * AddGuestToPartyAcitivty Constructor.
     *
     * @param userDao to initialize the userDAO;
     * @param playlistDao to initialize the playlistDao
     */
    @Inject
    public AddGuestToPartyActivity(UserDAO userDao, PlaylistDao playlistDao) {
        this.userDAO = userDao;
        this.playlistDao = playlistDao;
    }


    /**
     * This method handles the incoming request by adding a guest
     * to the party and persisting the updated guestList.
     * <p>
     * It then returns the updated guestList.
     * <p>
     * If the user does not exist, this should throw a UserNotFoundException.
     *
     * @param addGuestToPartyRequest request object containing the user ID
     *                                 to retrieve the user data
     * @return addGuestToPartyResult result object containing the party's updated list of
     *                                 API defined {@link UserModel}s
     */


    public AddGuestToPartyResult handleRequest(final AddGuestToPartyRequest addGuestToPartyRequest) {
        log.info("Received AddGuestToPartyRequest {} ", addGuestToPartyRequest);

        User newGuest = new User();
        newGuest.setFirstName(addGuestToPartyRequest.getFirstName());
        newGuest.setLastName(addGuestToPartyRequest.getLastName());
        newGuest.setIsHost(addGuestToPartyRequest.isAdmin());
        newGuest.setSongsAdded(addGuestToPartyRequest.getSongsAdded());
        newGuest.setSongsUpvoted(addGuestToPartyRequest.getSongsUpvoted());
        userDAO.addGuestToParty(newGuest);
        log.info("Created a new guest object to the table");

        Playlist playlist = playlistDao.getPlaylist(addGuestToPartyRequest.getPlaylistId());
        List<User> guestList = playlist.getGuests();
        guestList.add(newGuest);
        playlist.setGuests(guestList);
        playlistDao.savePlaylist(playlist);

        UserModel userModel = new ModelConverter().toUserModel(newGuest);

        return AddGuestToPartyResult.builder()
                .withUserModel(userModel)
                .build();
    }
}
