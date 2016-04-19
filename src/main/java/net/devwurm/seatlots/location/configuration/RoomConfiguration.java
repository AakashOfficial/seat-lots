package net.devwurm.seatlots.location.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import net.devwurm.seatlots.json.IntegerPropertyDeserializer;

/**
 * Data class for the configuration of a specific room
 */
public class RoomConfiguration {
    @JsonProperty
    @JsonDeserialize(using = IntegerPropertyDeserializer.class)
    private IntegerProperty number;

    @JsonProperty
    @JsonDeserialize(using = IntegerPropertyDeserializer.class)
    private IntegerProperty capacity;

    /**
     * Constructor for Jackson
     */
    private RoomConfiguration() {}

    public RoomConfiguration(IntegerProperty number, IntegerProperty capacity) {
        this.number = number;
        this.capacity = capacity;
    }

    public RoomConfiguration(Integer number, Integer capacity) {
        this.number = new SimpleIntegerProperty();
        this.number.setValue(number);

        this.capacity = new SimpleIntegerProperty();
        this.capacity.setValue(capacity);
    }

    public int getNumber() {
        return number.get();
    }

    public IntegerProperty numberProperty() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number.setValue(number);
    }

    @JsonProperty("number")
    public void setNumber(IntegerProperty number) {
        this.number = number;
    }

    public int getCapacity() {
        return capacity.get();
    }

    public IntegerProperty capacityProperty() {
        return capacity;
    }


    public void setCapacity(Integer capacity) {
        this.capacity.setValue(capacity);
    }

    @JsonProperty("capacity")
    public void setCapacity(IntegerProperty capacity) {
        this.capacity = capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoomConfiguration that = (RoomConfiguration) o;

        return number.equals(that.number);

    }

    @Override
    public int hashCode() {
        return number.hashCode();
    }
}
