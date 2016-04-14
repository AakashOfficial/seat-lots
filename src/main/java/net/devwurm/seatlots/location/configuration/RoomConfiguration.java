package net.devwurm.seatlots.location.configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Class for describing a room configuration
 */
public class RoomConfiguration {
    private String name;
    private HashMap<Integer, Integer> configuration = new HashMap<>();

    public RoomConfiguration(String name) {
        this.name = name;
    }

    public RoomConfiguration(String name, HashMap<Integer, Integer> configuration) {
        this.configuration = configuration;
        this.name = name;
    }

    public void addRoom(Integer room, Integer capacity) {
        if (!configuration.containsKey(room)) {
            configuration.put(room, capacity);
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
        if (configuration.containsKey(room)) {
            configuration.put(room, newCapacity);
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
        return name;
    }

    public Set<Map.Entry<Integer, Integer>> getConfiguration() {
        return configuration.entrySet();
    }

}
