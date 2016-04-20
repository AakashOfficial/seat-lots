package net.devwurm.seatlots.location;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class for describing a list of rooms
 */
public class RoomList {
    @JsonProperty
    private String name;

    @JsonProperty
    @JsonDeserialize(as = ArrayList.class)
    private List<Room> rooms;

    /**
     * Jackson constructor
     */
    private RoomList() {
    }

    public RoomList(String name) {
        this.name = name;
        this.rooms = new ArrayList<>();
    }

    public RoomList(String name, List<Room> rooms) {
        this.name = name;
        this.rooms = rooms;
    }

    public static RoomList fromJSON(String jsonString) throws IOException, JsonMappingException, JsonParseException {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(jsonString, RoomList.class);
    }

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
            rooms.remove(pos.intValue());
        }
    }

    public void removeRoomByNumber(Integer roomNumber) {
        Optional<Room> r = getRoomByNumber(roomNumber);

        if (r.isPresent()) {
            rooms.remove(r.get());
        }
    }

    @JsonIgnore
    public Integer getNumberOfRooms() {
        return rooms.size();
    }

    @JsonIgnore
    public Integer getCapacity() {
        return rooms.stream()
                .map(Room::getCapacity)
                .reduce(0, Integer::sum);
    }

    public String getName() {
        return name;
    }

    public String toJSON() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(this);
    }
}
