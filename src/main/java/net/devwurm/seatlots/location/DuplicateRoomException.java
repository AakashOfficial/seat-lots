package net.devwurm.seatlots.location;

/**
 * Exception which can be thrown if a Room is already inside a scope, where all rooms should be unique
 */
public class DuplicateRoomException extends IllegalArgumentException {
    private Integer room;

    public DuplicateRoomException(String message, Integer room) {
        super(message);
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

    public Integer getRoom() {
        return room;
    }
}
