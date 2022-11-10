package com.nashss.se.partyplaylist.exceptions;

public class UserNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -929330263478684434L;

    public UserNotFoundException() {
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundException(Throwable cause) {
        super(cause);
    }
}
