package com.nashss.se.partyplaylist.test.helper;

import com.nashss.se.partyplaylist.dynamodb.models.User;

import java.util.List;

public final class UserTestHelper {

    private UserTestHelper() {
    }

    public static User generateUser() {
        User user = new User();
        user.setUserId("userId");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setIsHost(false);
        user.setSongsAdded(List.of("song1", "song2"));
        user.setSongsUpvoted(List.of("song1", "song2"));
        return user;
    }

}

