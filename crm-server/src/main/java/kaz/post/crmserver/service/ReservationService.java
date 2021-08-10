package kaz.post.crmserver.service;


import kaz.post.crmserver.entity.rooms.Room;
import kaz.post.crmserver.entity.users.User;

public interface ReservationService {
    String makePayment(Room room, User user);
}
