package com.nashss.se.musicplaylistservice.activity;

import com.nashss.se.partyplaylist.activity.AddGuestToPartyActivity;
import com.nashss.se.partyplaylist.activity.requests.AddGuestToPartyRequest;
import com.nashss.se.partyplaylist.activity.results.AddGuestToPartyResult;
import com.nashss.se.partyplaylist.dynamodb.UserDAO;
import com.nashss.se.partyplaylist.dynamodb.models.User;
import com.nashss.se.partyplaylist.test.helper.UserTestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class AddGuestToPartyActivityTest {

    @Mock
    private UserDAO userDAO;

    private AddGuestToPartyActivity addGuestToPartyActivity;

    @BeforeEach
    void setup() {
        openMocks(this);
        addGuestToPartyActivity = new AddGuestToPartyActivity(userDAO);
    }

    @Test
    void handleRequest_validRequest_addGuestToParty() {
        // GIVEN

        User guest = UserTestHelper.generateUser();
        String userId = guest.getUserId();
        String firstName = guest.getFirstName();
        String lastName = guest.getLastName();

        // WHEN
        when(userDAO.getGuest(userId)).thenReturn(guest);

        AddGuestToPartyRequest request = AddGuestToPartyRequest.builder()
                .withUserId(userId)
                .withFirstName(firstName)
                .withLastName(lastName)
                .build();

        AddGuestToPartyResult result = addGuestToPartyActivity.handleRequest(request);

        verify(userDAO).addGuestToParty(guest);

    }

}
