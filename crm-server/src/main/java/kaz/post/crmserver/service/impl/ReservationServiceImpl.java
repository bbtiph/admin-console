package kaz.post.crmserver.service.impl;

import kaz.post.crmserver.entity.reservation.Reservation;
import kaz.post.crmserver.entity.rooms.Room;
import kaz.post.crmserver.entity.users.User;
import kaz.post.crmserver.repositories.ReservationRepository;
import kaz.post.crmserver.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public String makePayment(Room room, User user) {
        Reservation reservation = null;
        Date date = new Date();
        SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
        String stringDate= DateFor.format(date);
        reservation.setDateIssue(stringDate);
        reservation.setRoom(room);
        reservation.setUser(user);
        reservationRepository.saveAndFlush(reservation);
        return reservation.toString();
    }
}
