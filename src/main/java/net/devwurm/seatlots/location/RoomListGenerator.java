package net.devwurm.seatlots.location;

import net.devwurm.seatlots.location.configuration.RoomConfiguration;
import net.devwurm.seatlots.location.configuration.RoomListConfiguration;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Class for generating a RoomList from a given RoomListConfiguration
 */
public class RoomListGenerator {

    public RoomList generateRoomList(RoomListConfiguration configuration) {
        List<RoomConfiguration> configurationSet = configuration.getConfiguration();

        List<Room> rooms = configurationSet.stream()
                .map(e -> new Room(e.getNumber(), e.getCapacity()))
                .collect(Collectors.toList());

        return new RoomList(configuration.getName(), rooms);
    }
}
