package com.nashss.se.partyplaylist.activity;

import com.nashss.se.partyplaylist.activity.requests.CreatePlaylistRequest;
import com.nashss.se.partyplaylist.activity.requests.GetPlaylistRequest;
import com.nashss.se.partyplaylist.activity.results.CreatePlaylistResult;
import com.nashss.se.partyplaylist.activity.results.GetPlaylistResult;
import com.nashss.se.partyplaylist.dynamodb.PlaylistDao;
import com.nashss.se.partyplaylist.dynamodb.models.Playlist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class GetPlaylistActivityTest {

    @Mock
    private PlaylistDao playlistDao;

    private GetPlaylistActivity getPlaylistActivity;

    @BeforeEach
    void setUp() {
        openMocks(this);
        getPlaylistActivity = new GetPlaylistActivity(playlistDao);
    }

    @Test
    public void handleRequest_savedPlaylistFound_returnsPlaylistModelInResult() {
        //GIVEN
        String expectedId = "PlaylistId";
        String expectedName = "PlaylistName";

        Playlist playlist = new Playlist();
        playlist.setPlaylistId(expectedId);
        playlist.setPlaylistName(expectedName);

        when(playlistDao.getPlaylist(expectedId)).thenReturn(playlist);

        GetPlaylistRequest request = GetPlaylistRequest.builder()
                .withId(expectedId)
                .build();

        //WHEN
        GetPlaylistResult result = getPlaylistActivity.handleRequest(request);

        //THEN
        assertEquals(expectedId, result.getPlaylist().getPlaylistID());
        assertEquals(expectedName, result.getPlaylist().getPlaylistName());
    }

}
