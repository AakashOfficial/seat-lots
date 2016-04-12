package net.devwurm.seatlots.location;

import java.util.ArrayList;

/**
 * Class for describing a room with seats
 */
public class Room {
    private final Integer number;
    private final ArrayList<Seat> seats = new ArrayList();

    public Room (Integer number, Integer numberOfSeats) {
        this.number = number;

        for (Integer i = 1; i <= numberOfSeats; i++) {
            seats.add(new Seat(i));
        }
    }

    public Integer getNumber() {
        return number;
    }

    public Seat getSeat(Integer seatNumber) {
        return seats.get(seatNumber);
    }

    public void removeSeat(Integer seatNumber) {
        seats.remove(seatNumber.intValue());
    }

    public Integer getCapacity () {
        return seats.size();
    }
}
