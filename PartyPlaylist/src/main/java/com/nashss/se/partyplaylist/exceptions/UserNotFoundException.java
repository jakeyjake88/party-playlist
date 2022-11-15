package com.nashss.se.partyplaylist.exceptions;

public class UserNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -929330263478684434L;

    /**
     * Creates a UserNotFoundException.
     */
    public UserNotFoundException() {
    }

    /**
     * Creates UserNotFoundException.
     * @param message string message to pass to client
     */
    public UserNotFoundException(String message) {

        super(message);
    }

    /**
     * Creates UserNotFoundException.
     * @param message string message to pass to client
     * @param cause Throwable that was thrown
     */
    public UserNotFoundException(String message, Throwable cause) {

        super(message, cause);
    }

    /**
     * Create UserNotFoundException.
     * @param cause Throwable that was thrown
     */
    public UserNotFoundException(Throwable cause) {

        super(cause);
    }
}
