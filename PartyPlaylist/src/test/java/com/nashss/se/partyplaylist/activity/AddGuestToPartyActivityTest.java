package com.nashss.se.partyplaylist.activity;

import com.nashss.se.partyplaylist.activity.requests.AddGuestToPartyRequest;
import com.nashss.se.partyplaylist.activity.results.AddGuestToPartyResult;
import com.nashss.se.partyplaylist.dynamodb.PlaylistDao;
import com.nashss.se.partyplaylist.dynamodb.UserDAO;

import com.nashss.se.partyplaylist.dynamodb.models.Playlist;
import com.nashss.se.partyplaylist.dynamodb.models.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class AddGuestToPartyActivityTest {

    @Mock
    private UserDAO userDAO;
    @Mock
    private PlaylistDao playlistDao;

    private AddGuestToPartyActivity addGuestToPartyActivity;

    @BeforeEach
    void setup() {
        openMocks(this);
        addGuestToPartyActivity = new AddGuestToPartyActivity(userDAO, playlistDao);
    }

    @Test
    public void handleRequest_addGuest_createsAndSavesGuestInUserDao() {
        String playlistId = "01";
        String firstName = "Walter";
        String lastName = "White";

        Playlist playlist = new Playlist();
        playlist.setPlaylistId(playlistId);

        when(playlistDao.getPlaylist(playlistId)).thenReturn(playlist);

        AddGuestToPartyRequest request = AddGuestToPartyRequest.builder()
                .withFirstName(firstName)
                .withLastName(lastName).build();

        //WHEN
        AddGuestToPartyResult result = addGuestToPartyActivity.handleRequest(request);

        //THEN
        verify(userDAO).addGuestToParty(any(User.class));

        assertEquals(firstName, result.getGuest().getFirstName());
        assertEquals(lastName, result.getGuest().getLastName());
    }

    @Test
    public void handleRequest_addGuest_createsAndSavesGuestInPlaylistDao() {
        String playlistId = "01";
        String firstName = "Walter";
        String lastName = "White";
        Set<String> guestList = new HashSet<>();

        Playlist playlist = new Playlist();
        playlist.setPlaylistId(playlistId);
        playlist.setGuests(guestList);

        when(playlistDao.getPlaylist(playlistId)).thenReturn(playlist);

        AddGuestToPartyRequest request = AddGuestToPartyRequest.builder()
                .withFirstName(firstName)
                .withLastName(lastName).build();

        //WHEN
        AddGuestToPartyResult result = addGuestToPartyActivity.handleRequest(request);

        //THEN
        verify(playlistDao).savePlaylist(any(Playlist.class));
    }
}
