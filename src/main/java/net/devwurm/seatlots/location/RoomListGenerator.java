package net.devwurm.seatlots.location;

import net.devwurm.seatlots.location.configuration.RoomConfiguration;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Class for generating a RoomList from a given RoomConfiguration
 */
public class RoomListGenerator {

    public RoomList generateRoomList(RoomConfiguration configuration) {
        Set<Map.Entry<Integer, Integer>> configurationSet = configuration.getConfiguration();

        List<Room> rooms = configurationSet.stream()
                .map(e -> new Room(e.getKey(), e.getValue()))
                .collect(Collectors.toList());

        return new RoomList(rooms);
    }
}
