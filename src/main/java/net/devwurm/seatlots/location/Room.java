package net.devwurm.seatlots.location;

import java.util.ArrayList;

/**
 * Class for describing a room with seats
 */
public class Room {
    private final Integer number;
    private final ArrayList<Seat> seats = new ArrayList<Seat>();

    public Room(Integer number, Integer numberOfSeats) {
        this.number = number;

        for (Integer i = 1; i <= numberOfSeats; i++) {
            seats.add(new Seat(i));
        }
    }

    public Integer getNumber() {
        return number;
    }

    public Seat getSeatAt(Integer seatNumber) {
        return seats.get(seatNumber);
    }

    public void removeSeatAt(Integer seatNumber) {
        seats.remove(seatNumber.intValue());
    }

    public Integer getCapacity() {
        return seats.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;

        return number.equals(room.number);

    }

    @Override
    public int hashCode() {
        return number.hashCode();
    }
}
