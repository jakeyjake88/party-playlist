package com.nashss.se.partyplaylist.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.List;
import java.util.Objects;

/**
 * Represents a User in the user table.
 */

@DynamoDBTable(tableName = "users")
public class User {
    private String userId;
    private String firstName;
    private String lastName;
    private Boolean isHost;
    private List<String> songsAdded;
    private List<String> songsUpvoted;

    @DynamoDBIndexHashKey(attributeName = "userId")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @DynamoDBAttribute(attributeName = "firstName")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @DynamoDBAttribute(attributeName = "lastName")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @DynamoDBAttribute(attributeName = "isHost")
    public Boolean isHost() {
        return isHost;
    }

    public void setIsHost(Boolean isHost) {
        this.isHost = isHost;
    }

    @DynamoDBAttribute(attributeName = "songsAdded")
    public List<String> getSongsAdded() {
        return songsAdded;
    }

    public void setSongsAdded(List<String> songsAdded) {
        this.songsAdded = songsAdded;
    }

    @DynamoDBAttribute(attributeName = "songsUpvoted")
    public List<String> getSongsUpvoted() {
        return songsUpvoted;
    }

    public void setSongsUpvoted(List<String> songsUpvoted) {
        this.songsUpvoted = songsUpvoted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, firstName, lastName);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", isHost=" + isHost +
                ", songsAdded=" + songsAdded +
                ", songsUpvoted=" + songsUpvoted +
                '}';
    }
}
