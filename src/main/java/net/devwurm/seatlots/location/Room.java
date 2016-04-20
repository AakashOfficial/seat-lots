package net.devwurm.seatlots.location;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class for describing a room with seats
 */
public class Room {
    @JsonProperty
    private Integer number;

    @JsonProperty
    @JsonDeserialize(as = ArrayList.class)
    private List<Seat> seats = new ArrayList<Seat>();

    /**
     * Jackson constructor
     */
    private Room() {}

    public Room(Integer number, Integer numberOfSeats) {
        this.number = number;

        for (Integer i = 1; i <= numberOfSeats; i++) {
            seats.add(new Seat(i));
        }
    }

    public Integer getNumber() {
        return number;
    }


    @JsonIgnore
    public Integer getCapacity() {
        return seats.size();
    }

    public Optional<Seat> getSeatAt(Integer seatNumber) {
        if (seatNumber < seats.size()) {
            return Optional.of(seats.get(seatNumber));
        } else {
            return Optional.empty();
        }
    }

    public void removeSeatAt(Integer seatNumber) {
        if (seatNumber < seats.size()) {
            seats.remove(seatNumber.intValue());
        }
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
