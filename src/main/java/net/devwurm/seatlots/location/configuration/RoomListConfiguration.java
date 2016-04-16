package net.devwurm.seatlots.location.configuration;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import net.devwurm.seatlots.location.DuplicateRoomException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Class for describing a room list configuration
 */
public class RoomListConfiguration {
    private StringProperty name;
    private ObservableSet<RoomConfiguration> configuration = FXCollections.observableSet();

    public RoomListConfiguration(String name) {
        this.name = new SimpleStringProperty();
        this.name.setValue(name);
    }

    public RoomListConfiguration(StringProperty name) {
        this.name = name;
    }

    public void addRoom(Integer room, Integer capacity) {
        RoomConfiguration tempRoomConfiguration = new RoomConfiguration(room, capacity);

        if (!configuration.contains(room)) {
            configuration.add(tempRoomConfiguration);
        } else {
            throw new DuplicateRoomException("Room already included in configuration", room);
        }
    }

    public void addRoom(String room, String capacity) {
        Integer roomInt;
        Integer capacityInt;

        try {
            roomInt = Integer.parseInt(room);
        } catch (NumberFormatException e) {
            throw new IllegalRoomStringException(e.getMessage(), room);
        }

        try {
            capacityInt = Integer.parseInt(capacity);
        } catch (NumberFormatException e) {
            throw new IllegalCapacityStringException(e.getMessage(), capacity);
        }

        addRoom(roomInt, capacityInt);
    }

    public void removeRoom(Integer room) {
        configuration.remove(room);
    }

    public void removeRoom(String room) {
        Integer roomInt;

        try {
            roomInt = Integer.parseInt(room);
        } catch (NumberFormatException e) {
            throw new IllegalRoomStringException(e.getMessage(), room);
        }

        removeRoom(roomInt);
    }

    public void updateRoom(Integer room, Integer newCapacity) {
        RoomConfiguration tempRoomConfiguration = new RoomConfiguration(room, newCapacity);

        if (configuration.contains(tempRoomConfiguration)) {
            configuration.remove(tempRoomConfiguration);
            configuration.add(tempRoomConfiguration);
        } else {
            throw new RoomNotPresentException("Room not in configuration", room);
        }
    }

    public void updateRoom(String room, String newCapacity) {
        Integer roomInt;
        Integer newCapacityInt;

        try {
            roomInt = Integer.parseInt(room);
        } catch (NumberFormatException e) {
            throw new IllegalRoomStringException(e.getMessage(), room);
        }

        try {
            newCapacityInt = Integer.parseInt(newCapacity);
        } catch (NumberFormatException e) {
            throw new IllegalCapacityStringException(e.getMessage(), newCapacity);
        }

        updateRoom(roomInt, newCapacityInt);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public ObservableSet<RoomConfiguration> getConfiguration() {
        return configuration;
    }

}
