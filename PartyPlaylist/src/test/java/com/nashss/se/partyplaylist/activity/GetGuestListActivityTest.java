package com.nashss.se.partyplaylist.activity;

import com.nashss.se.partyplaylist.activity.requests.GetGuestListRequest;
import com.nashss.se.partyplaylist.activity.results.GetGuestListResult;
import com.nashss.se.partyplaylist.dynamodb.PlaylistDao;
import com.nashss.se.partyplaylist.dynamodb.models.Playlist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class GetGuestListActivityTest {
    @Mock
    private PlaylistDao playlistDao;

    private GetGuestListActivity getGuestListActivity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        getGuestListActivity = new GetGuestListActivity(playlistDao);
    }

    @Test
    public void handleRequest_savedGuestListFound_returnsGuestListModelInResult() {
        // GIVEN
        String expectedId = "expectedId";
        String expectedName = "expectedName";
        Set<String> guestList = new HashSet<>();
        guestList.add(expectedName);

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
        assertTrue(result.getGuestList().contains(expectedName));
    }
}
