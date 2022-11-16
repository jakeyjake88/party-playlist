package com.nashss.se.partyplaylist.dynamodb.models;

import com.nashss.se.partyplaylist.converters.SongListConverter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;

import java.util.List;
import java.util.Objects;

/**
 * Represents a User in the user table.
 */

@DynamoDBTable(tableName = "users")
public class User {
    private String firstName;
    private String lastName;
    private Boolean isHost;
    private List<Song> songsAdded;
    private List<Song> songsUpvoted;

    @DynamoDBHashKey(attributeName = "firstName")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @DynamoDBRangeKey(attributeName = "lastName")
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

    @DynamoDBTypeConverted(converter = SongListConverter.class)
    @DynamoDBAttribute(attributeName = "songsAdded")
    public List<Song> getSongsAdded() {
        return songsAdded;
    }

    public void setSongsAdded(List<Song> songsAdded) {
        this.songsAdded = songsAdded;
    }

    @DynamoDBTypeConverted(converter = SongListConverter.class)
    @DynamoDBAttribute(attributeName = "songsUpvoted")
    public List<Song> getSongsUpvoted() {
        return songsUpvoted;
    }

    public void setSongsUpvoted(List<Song> songsUpvoted) {
        this.songsUpvoted = songsUpvoted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;
        return Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", isHost=" + isHost +
                ", songsAdded=" + songsAdded +
                ", songsUpvoted=" + songsUpvoted +
                '}';
    }
}
