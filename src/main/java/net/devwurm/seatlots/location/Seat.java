package net.devwurm.seatlots.location;

/**
 * Class for describing a seat
 */
public class Seat {
    private final Integer number;

    public Seat (Integer number) {
        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Seat seat = (Seat) o;

        return number.equals(seat.number);

    }

    @Override
    public int hashCode() {
        return number.hashCode();
    }
}
