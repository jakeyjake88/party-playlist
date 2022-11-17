package com.nashss.se.partyplaylist.activity;

import com.nashss.se.partyplaylist.activity.requests.CreatePlaylistRequest;
import com.nashss.se.partyplaylist.activity.results.CreatePlaylistResult;
import com.nashss.se.partyplaylist.dynamodb.PlaylistDao;
import com.nashss.se.partyplaylist.dynamodb.models.Playlist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

public class CreatePlaylistActivityTest {

    @Mock
    private PlaylistDao playlistDao;

    private CreatePlaylistActivity createPlaylistActivity;

    @BeforeEach
    void setUp() {
        openMocks(this);
        createPlaylistActivity = new CreatePlaylistActivity(playlistDao);
    }

    @Test
    public void handleRequest_createPlaylist_createAndSavePlaylist() {
        //GIVEN
        String expectedName = "PlaylistName";

        CreatePlaylistRequest request = CreatePlaylistRequest.builder()
                .withName(expectedName)
                .build();

        //WHEN
        CreatePlaylistResult result = createPlaylistActivity.handleRequest(request);

        //THEN
        verify(playlistDao).savePlaylist(any(Playlist.class));

        assertNotNull(result.getPlaylist().getPlaylistID());
        assertEquals(expectedName, result.getPlaylist().getPlaylistName());
    }

}
