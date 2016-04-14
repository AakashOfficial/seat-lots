package net.devwurm.seatlots.location.configuration;

/**
 * Exception which can be thrown if a Room string is incorrect
 */
public class IllegalRoomStringException extends IllegalArgumentException {
    private String roomString;

    public IllegalRoomStringException(String s, String roomString) {
        super(s);
        this.roomString = roomString;
    }

    public IllegalRoomStringException(String s, Throwable throwable, String roomString) {
        super(s, throwable);
        this.roomString = roomString;
    }

    public IllegalRoomStringException(Throwable throwable, String roomString) {
        super(throwable);
        this.roomString = roomString;
    }

    public IllegalRoomStringException() {
        super();
    }

    public String getRoomString() {
        return roomString;
    }
}
