package ar.edu.itba.paw.interfaces;

public interface RoomService {

    List<Room> getRoomsList();

    Room getRoom(long roomID);
}
