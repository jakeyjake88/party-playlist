package com.nashss.se.partyplaylist.activity;

import com.nashss.se.partyplaylist.activity.requests.AddGuestToPartyRequest;
import com.nashss.se.partyplaylist.activity.results.AddGuestToPartyResult;
import com.nashss.se.partyplaylist.dynamodb.UserDAO;
import com.nashss.se.partyplaylist.dynamodb.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

/*public class AddGuestToPartyActivityTest {

    @Mock
    private UserDAO userDAO;

    private AddGuestToPartyActivity addGuestToPartyActivity;

    @BeforeEach
    void setUp() {
        openMocks(this);
        addGuestToPartyActivity = new AddGuestToPartyActivity(userDAO);
    }*/

/*    @Test
    public void handleRequest_addGuest_createsAndSavesGuest() {
        String firstName = "Walter";
        String lastName = "White";
        String userId = "46920";

        AddGuestToPartyRequest request = AddGuestToPartyRequest.builder()
                .withUserId(userId)
                .withFirstName(firstName)
                .withLastName(lastName).build();

        //WHEN
        AddGuestToPartyResult result = addGuestToPartyActivity.handleRequest(request);

        //THEN
        verify(userDAO).addGuestToParty(any(User.class));

        assertNotNull(result.getGuestList());
        assertEquals(firstName, result.getGuestList().get(0).getFirstName());
    }*/
/*}*/
