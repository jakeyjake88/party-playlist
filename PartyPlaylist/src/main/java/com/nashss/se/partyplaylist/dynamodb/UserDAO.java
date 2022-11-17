package com.nashss.se.partyplaylist.dynamodb;

import com.nashss.se.partyplaylist.dynamodb.models.User;
import com.nashss.se.partyplaylist.exceptions.UserNotFoundException;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;



@Singleton
public class UserDAO {

    private final DynamoDBMapper dynamoDBMapper;

    /**
     * Instantiates an UserDAO object.
     *
     * @param dynamoDbMapper the {@link DynamoDBMapper} used to interact with the users table
     */

    @Inject
    public UserDAO(DynamoDBMapper dynamoDbMapper) {
        this.dynamoDBMapper = dynamoDbMapper;
    }

    /**
     * Adds the user to the party.
     *
     * @param user The user to add
     */
    public void addGuestToParty(User user) {
        this.dynamoDBMapper.save(user);
    }

    /**
     * Retrieves a user by first name and last name.
     * <p>
     * If not found, throws UserNotFoundException.
     *
     * @param userId the first name to look up
     * @return The corresponding user if found
     */

    public User getGuest(String userId) {

        User user = dynamoDBMapper.load(User.class, userId);
        if (user == null) {
            throw new UserNotFoundException(
                    String.format("Could not find User '%s'", userId));
        }
        return user;
    }

    /**
     * Retrieves the entire Guest List at the party.
     *
     * @return The current Guest List
     */
    public List<User> getGuestList() {
        List<User> guestList = new ArrayList<>();
        final ObjectMapper mapper = new ObjectMapper();

        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();

        ScanRequest scanRequest = new ScanRequest()
                .withTableName("users");

        ScanResult result = client.scan(scanRequest);
        List<Map<String, AttributeValue>> userList = result.getItems();

        for (Map<String, AttributeValue> entry : userList) {
            final User guest = mapper.convertValue(userList, User.class);
            guestList.add(guest);
        }

        return guestList;
    }
}
