package com.nashss.se.partyplaylist.activity;

import com.nashss.se.partyplaylist.activity.requests.GetGuestListRequest;
import com.nashss.se.partyplaylist.activity.results.GetGuestListResult;
import com.nashss.se.partyplaylist.dynamodb.PlaylistDao;
import com.nashss.se.partyplaylist.dynamodb.models.Playlist;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;

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
     * @return getGuestListResult result object containing the API defined {@link Set<String> }
     */

    public GetGuestListResult handleRequest(final GetGuestListRequest getGuestListRequest) {
        log.info("Received GetGuestListRequest {}", getGuestListRequest);

        String playlistId = getGuestListRequest.getPlaylistId();
        Playlist playlist = playlistDao.getPlaylist(playlistId);
        Set<String> guestList = playlist.getGuests();

        return GetGuestListResult.builder()
                .withGuestList(guestList)
                .build();
    }

}
