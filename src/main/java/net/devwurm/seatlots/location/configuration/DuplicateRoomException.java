package net.devwurm.seatlots.location.configuration;

/**
 * Exception which can be thrown if a Room string is already inside the configuration
 */
public class DuplicateRoomException extends IllegalArgumentException {
    private Integer room;

    public DuplicateRoomException(String s, Integer room) {
        super(s);
        this.room = room;
    }

    public DuplicateRoomException(String s, Throwable throwable, Integer roomString) {
        super(s, throwable);
        this.room = roomString;
    }

    public DuplicateRoomException(Throwable throwable, Integer roomString) {
        super(throwable);
        this.room = roomString;
    }

    public DuplicateRoomException() {
        super();
    }

    public Integer getRoomString() {
        return room;
    }
}
