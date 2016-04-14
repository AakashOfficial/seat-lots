package net.devwurm.seatlots.location.configuration;

/**
 * Exception which can be thrown if a Room string is incorrect
 */
public class IllegalCapacityStringException extends IllegalArgumentException {
    private String capacityString;

    public IllegalCapacityStringException(String s, String capacityString) {
        super(s);
        this.capacityString = capacityString;
    }

    public IllegalCapacityStringException(String s, Throwable throwable, String capacityString) {
        super(s, throwable);
        this.capacityString = capacityString;
    }

    public IllegalCapacityStringException(Throwable throwable, String capacityString) {
        super(throwable);
        this.capacityString = capacityString;
    }

    public IllegalCapacityStringException() {
        super();
    }

    public String getCapacityString() {
        return capacityString;
    }
}
