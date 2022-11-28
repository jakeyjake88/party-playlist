package com.nashss.se.partyplaylist.activity;

import com.nashss.se.partyplaylist.activity.requests.GetGuestListRequest;
import com.nashss.se.partyplaylist.activity.results.GetGuestListResult;
import com.nashss.se.partyplaylist.dynamodb.PlaylistDao;
import com.nashss.se.partyplaylist.dynamodb.models.Playlist;
import com.nashss.se.partyplaylist.dynamodb.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class GetGuestListActivityTest {
    @Mock
    private PlaylistDao playlistDao;

    private GetGuestListActivity getGuestListActivity;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        getGuestListActivity = new GetGuestListActivity(playlistDao);
    }

    @Test
    public void handleRequest_savedGuestListFound_returnsGuestListModelInResult() {
        // GIVEN
        String expectedId = "expectedId";
        User user = new User();
        user.setFirstName("expectedFirstName");
        user.setLastName("expectedLastName");
        List<User> guestList = new ArrayList<>();
        guestList.add(user);

        Playlist playlist = new Playlist();
        playlist.setPlaylistId(expectedId);
        playlist.setGuests(guestList);

        when(playlistDao.getPlaylist(expectedId)).thenReturn(playlist);

        GetGuestListRequest request = GetGuestListRequest.builder()
                .withPlaylistId(expectedId)
                .build();

        // WHEN
        GetGuestListResult result = getGuestListActivity.handleRequest(request);

        // THEN
        assertEquals(user.getFirstName(), result.getGuestList().get(0).getFirstName());
        assertEquals(user.getLastName(), result.getGuestList().get(0).getLastName());
    }
}