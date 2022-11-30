package com.nashss.se.partyplaylist.activity;

import com.nashss.se.partyplaylist.dynamodb.PlaylistDao;
import com.nashss.se.partyplaylist.dynamodb.models.Playlist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
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
        String expectedFirstName = "expectedFirstName";
        String expectedLastName = "expectedLastName";

        Playlist playlist = new Playlist();
    }
}