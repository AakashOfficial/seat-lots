package net.devwurm.seatlots.location.configuration;

/**
 * Exception which can be thrown if a Room string is already inside the configuration
 */
public class RoomNotPresentException extends IllegalArgumentException {
    private Integer room;

    public RoomNotPresentException(String s, Integer room) {
        super(s);
        this.room = room;
    }

    public RoomNotPresentException(String s, Throwable throwable, Integer roomString) {
        super(s, throwable);
        this.room = roomString;
    }

    public RoomNotPresentException(Throwable throwable, Integer roomString) {
        super(throwable);
        this.room = roomString;
    }

    public RoomNotPresentException() {
        super();
    }

    public Integer getRoomString() {
        return room;
    }
}
