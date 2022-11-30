package com.nashss.se.partyplaylist.exceptions;

public class ArtistLimitException extends RuntimeException {

    private static final long serialVersionUID = 425516801585266296L;

    /**
     * Exception with no message or cause.
     */
    public ArtistLimitException() {
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public ArtistLimitException(String message) {
        super(message);
    }

    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */

    public ArtistLimitException(String message, Throwable cause) {
        super(message, cause);
    }


    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public ArtistLimitException(Throwable cause) {
        super(cause);
    }
}
