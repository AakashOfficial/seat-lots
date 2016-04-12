package net.devwurm.seatlots.location;

/**
 * Exception which gets thrown if two rooms with the same number get inserted into the same RoomList
 */
public class DuplicateRoomException extends RuntimeException {
    Integer collidingRoomNumber;

    public DuplicateRoomException (String message, Integer collidingRoomNumber) {
        super(message);

        this.collidingRoomNumber = collidingRoomNumber;
    }

    public Integer getCollidingRoomNumber() {
        return collidingRoomNumber;
    }
}
