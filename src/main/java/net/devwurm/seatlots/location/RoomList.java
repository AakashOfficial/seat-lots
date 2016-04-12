package net.devwurm.seatlots.location;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Class for describing a list of rooms
 */
public class RoomList {
    private final ArrayList<Room> rooms = new ArrayList<Room>();

    public void addRoom(Room room) {
        if (rooms.indexOf(room) >= 0) {
            throw new DuplicateRoomException("The same room is added into the same RoomList again", room.getNumber());
        } else {
            rooms.add(room);
        }
    }

    public Optional<Room> getRoomAt(Integer pos) {
        if (pos < rooms.size()) {
            return Optional.of(rooms.get(pos));
        } else {
            return Optional.empty();
        }
    }

    public Optional<Room> getRoomByNumber(Integer roomNumber) {
        for (Room r : rooms) {
            if (r.getNumber().equals(roomNumber)) return Optional.of(r);
        }

        return Optional.empty();
    }

    public void removeRoomAt(Integer pos) {
        if (pos < rooms.size()) {
            rooms.remove(pos);
        }
    }

    public void removeRoomByNumber(Integer roomNumber) {
        Optional<Room> r = getRoomByNumber(roomNumber);

        if (r.isPresent()) {
            rooms.remove(r);
        }
    }

    public Integer getNumberOfRooms() {
        return rooms.size();
    }

    public Integer getCapacity() {
        return rooms.stream()
                .map(Room::getCapacity)
                .reduce(0, Integer::sum);
    }
}
