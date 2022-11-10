package com.nashss.se.partyplaylist.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.nashss.se.partyplaylist.dynamodb.models.User;
import com.nashss.se.partyplaylist.exceptions.UserNotFoundException;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class UserDAO {

    private final DynamoDBMapper dynamoDBMapper;

    /**
     * Instantiates an UserDAO object.
     *
     * @param dynamoDbMapper the {@link DynamoDBMapper} used to interact with the users table
     */

    @Inject
    public UserDAO(DynamoDBMapper dynamoDbMapper) { this.dynamoDBMapper = dynamoDbMapper; }

    /**
     * Retrieves a user by userId.
     *
     * If not found, throws UserNotFoundException.
     *
     * @param userId The User ID to look up
     * @return The corresponding user if found
     */

    public User getUser(String userId) {

        User user = dynamoDBMapper.load(User.class, userId);
        if(user == null) {
            throw new UserNotFoundException(
                    String.format("Could not find User with UserId: '%s'", userId));
        }
        return user;
    }

    public List<User> getGuestList() {
        List<User> guestList = new ArrayList<>();
        guestList.
    }
}
