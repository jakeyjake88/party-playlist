package com.nashss.se.partyplaylist.exceptions;

/**
 * Exception to throw when a given firstName and lastName is already
 * in the database.
 */
public class UserAlreadyInGuestListException extends RuntimeException {
    private static final long serialVersionUID = 7848492395857801091L;

    /**
     * Exception with no message or cause.
     */
    public UserAlreadyInGuestListException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public UserAlreadyInGuestListException(String message) {
        super(message);
    }

    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public UserAlreadyInGuestListException(Throwable cause) {
        super(cause);
    }

    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public UserAlreadyInGuestListException(String message, Throwable cause) {
        super(message, cause);
    }
}
