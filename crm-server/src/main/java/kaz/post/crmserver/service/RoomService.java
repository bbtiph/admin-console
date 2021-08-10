package kaz.post.crmserver.service;

import kaz.post.crmserver.entity.rooms.Room;

import java.util.List;

public interface RoomService {

    String makeNotAvailable(Long id);

    List<Room> showAvailableRooms();

    void deleteRoom(Long id);

    void addRoom(Room room);

    Room findById(Long id);

    void updateRoom(Room room);


}
