package com.nashss.se.partyplaylist.activity;

import com.nashss.se.partyplaylist.activity.requests.GetHostRequest;
import com.nashss.se.partyplaylist.activity.results.GetHostResult;
import com.nashss.se.partyplaylist.dynamodb.PlaylistDao;
import com.nashss.se.partyplaylist.dynamodb.models.Playlist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class GetHostActivityTest {
    @Mock
    PlaylistDao playlistDao;

    private GetHostActivity getHostActivity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        getHostActivity = new GetHostActivity(playlistDao);
    }

    @Test
    void handleRequest_savedPlaylistFound_returnsPlaylistIdInResult() {
        //GIVEN
        String expectedId = "expectedId";
        String expectedPlaylistName = "expectedPlaylistName";
        String expectedFirstName = "expectedFirstName";
        String expectedLastName = "expectedLastName";
        String expectedHostName = expectedFirstName + " " + expectedLastName;

        Playlist playlist = new Playlist();
        playlist.setPlaylistId(expectedId);
        playlist.setHost(expectedHostName);

        when(playlistDao.getPlaylistWithPlaylistName(expectedPlaylistName)).thenReturn(playlist);

        GetHostRequest request = GetHostRequest.builder()
                .withPlaylistName(expectedPlaylistName)
                .withFirstName(expectedFirstName)
                .withLastName(expectedLastName)
                .build();

        // WHEN
        GetHostResult result = getHostActivity.handleRequest(request);

        // THEN
        assertEquals(expectedId, result.getPlaylistId());
    }
}