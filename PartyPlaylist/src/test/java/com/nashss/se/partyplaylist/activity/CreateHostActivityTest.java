package com.nashss.se.partyplaylist.activity;

import com.nashss.se.partyplaylist.activity.requests.CreateHostRequest;
import com.nashss.se.partyplaylist.activity.results.CreateHostResult;
import com.nashss.se.partyplaylist.dynamodb.PlaylistDao;
import com.nashss.se.partyplaylist.dynamodb.UserDAO;
import com.nashss.se.partyplaylist.dynamodb.models.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class CreateHostActivityTest {

    @Mock
    private UserDAO userDAO;

    @Mock
    private PlaylistDao playlistDao;

    private CreateHostActivity createHostActivity;

    @BeforeEach
    void setup() {
        openMocks(this);
        createHostActivity = new CreateHostActivity(userDAO);
    }

    @Test
    public void handleRequest_createHost_createsHostInUserDao() {
        String firstName = "George";
        String lastName = "Washington";
        String userId = "01";
        Boolean isHost = true;

        User host = new User();
        host.setFirstName(firstName);
        host.setLastName(lastName);
        host.setIsHost(true);

        when(userDAO.getGuest(userId)).thenReturn(host);

        CreateHostRequest request = CreateHostRequest.builder()
                .withFirstName(firstName)
                .withLastName(lastName)
                .build();

        CreateHostResult result = createHostActivity.handleRequest(request);

        verify(userDAO).addGuestToParty(any(User.class));

        assertEquals(firstName, result.getHost().getFirstName());
        assertEquals(lastName, result.getHost().getLastName());
        assertEquals(isHost, result.getHost().isHost());

    }

    @Test
    public void handleRequest_createHost_createsHostInPlaylistDao() {

        String firstName = "George";
        String lastName = "Washington";
        String playlistId = "01";
        Boolean isHost = true;

        User host = new User();
        host.setFirstName(firstName);
        host.setLastName(lastName);
        host.setIsHost(isHost);

        when(userDAO.getGuest(playlistId)).thenReturn(host);

        CreateHostRequest request = CreateHostRequest.builder()
                .withFirstName(firstName)
                .withLastName(lastName)
                .build();

        CreateHostResult result = createHostActivity.handleRequest(request);

        String hostName = result.getHost().getFirstName() + " " + result.getHost().getLastName();

        when(playlistDao.getHost(playlistId)).thenReturn(hostName);

        assertEquals(hostName, playlistDao.getHost(playlistId));
    }

}
