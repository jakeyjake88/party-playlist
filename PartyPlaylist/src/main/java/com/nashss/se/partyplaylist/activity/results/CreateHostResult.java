package com.nashss.se.partyplaylist.activity.results;

import com.nashss.se.partyplaylist.models.UserModel;

public class CreateHostResult {

    private final UserModel host;

    private CreateHostResult(UserModel host) {
        this.host = host;
    }

    public UserModel getHost() {
        return host;
    }

    @Override
    public String toString() {
        return "CreateHostResult{" +
                "host=" + host +
                '}';
    }

    /**
     * Creates a new Builder object.
     * @return new builder object
     */
    public static Builder builder() {
        return new Builder();
    }

    //CHECKSTYLE:OFF:Builder
    public static class Builder {
        private UserModel userModel;

        public Builder withUserModel(UserModel userModel) {
            this.userModel = userModel;
            return this;
        }

        public CreateHostResult build() {
            return new CreateHostResult(userModel);
        }
    }
}
