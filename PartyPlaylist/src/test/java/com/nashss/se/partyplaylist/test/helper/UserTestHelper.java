package com.nashss.se.partyplaylist.test.helper;

import com.nashss.se.partyplaylist.dynamodb.models.Song;
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
        user.setSongsAdded(List.of(new Song(), new Song()));
        user.setSongsUpvoted(List.of(new Song(), new Song()));
        return user;
    }

}

