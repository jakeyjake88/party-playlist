package com.nashss.se.partyplaylist.exceptions;

/**
 * Exception to throw when a given AlbumTrack ASIN and track number is not found
 * in the database.
 */
public class SongNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -3410734954584676544L;

    /**
     * Exception with no message or cause.
     */
    public SongNotFoundException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public SongNotFoundException(String message) {
        super(message);
    }

    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public SongNotFoundException(Throwable cause) {
        super(cause);
    }

    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public SongNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
