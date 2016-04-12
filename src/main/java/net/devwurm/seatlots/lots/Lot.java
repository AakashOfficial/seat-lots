package net.devwurm.seatlots.lots;

import net.devwurm.seatlots.location.Room;
import net.devwurm.seatlots.location.Seat;

/**
 * Class for describing a lot
 */
public class Lot {
    private final Integer roomNumber;
    private final Integer seatNumber;

    public Lot(Room room, Seat seat) {
        roomNumber = room.getNumber();
        seatNumber = seat.getNumber();
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lot lot = (Lot) o;

        if (!roomNumber.equals(lot.roomNumber)) return false;
        return seatNumber.equals(lot.seatNumber);

    }

    @Override
    public int hashCode() {
        int result = roomNumber.hashCode();
        result = 31 * result + seatNumber.hashCode();
        return result;
    }
}
