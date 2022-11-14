package com.nashss.se.partyplaylist.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.nashss.se.partyplaylist.dynamodb.UserDAO;
import com.nashss.se.partyplaylist.dynamodb.models.User;
import com.nashss.se.partyplaylist.exceptions.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class UserDAOTest {
    @Mock
    private DynamoDBMapper dynamoDBMapper;

    private UserDAO userDAO;

    @BeforeEach
    void setup() {
        openMocks(this);
        userDAO = new UserDAO(dynamoDBMapper);
    }

    @Test
    public void getGuest_withUserId_callsMapperWithParitionKey() {

        // GIVEN
        String userID = "userId";
        when(dynamoDBMapper.load(User.class, userID)).thenReturn(new User());

        // WHEN
        User user = userDAO.getGuest(userID);

        // THEN
        assertNotNull(user);
        verify(dynamoDBMapper).load(User.class, userID);

    }

    @Test
    public void getGuest_withInvalidUserId_throwsUserNotFoundException() {
        // GIVEN
        String badUserId = "badUserId";
        when(dynamoDBMapper.load(User.class, badUserId)).thenReturn(null);

        // WHEN + THEN
        assertThrows(UserNotFoundException.class, ()-> userDAO.getGuest(badUserId));

    }

}
