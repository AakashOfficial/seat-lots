package net.devwurm.seatlots.lots;

import net.devwurm.seatlots.location.Room;
import net.devwurm.seatlots.location.RoomList;
import net.devwurm.seatlots.location.Seat;

import java.util.Optional;
import java.util.Random;

/**
 * Class for performing draw actions on a RoomList to get lots
 */
public class LotDrawer {
    private final RoomList roomList;

    public LotDrawer(RoomList roomList) {
        this.roomList = roomList;
    }

    public Optional<Lot> drawLot() {
        if (roomList.getCapacity() <= 0) {
            return Optional.empty();
        } else {
            Random generator = new Random();

            Integer numOfRooms = roomList.getNumberOfRooms();
            Integer roomIndex = generator.nextInt(numOfRooms);
            Optional<Room> optionalRoom = roomList.getRoomAt(roomIndex);
            roomList.removeRoomAt(roomIndex);

            Room room;
            if (optionalRoom.isPresent()) {
                room = optionalRoom.get();
            } else {
                throw new IllegalStateException("Searched room not found in Room list although it should be there");
            }

            Integer numOfSeats = room.getCapacity();
            Integer seatIndex = generator.nextInt(numOfSeats);
            Optional<Seat> optionalSeat = room.getSeatAt(seatIndex);
            room.removeSeatAt(seatIndex);

            Seat seat;
            if (optionalSeat.isPresent()) {
                seat = optionalSeat.get();
            } else {
                throw new IllegalStateException("Searched seat not found in Room although it should be there");
            }


            return Optional.of(new Lot(room, seat));
        }
    }
}
