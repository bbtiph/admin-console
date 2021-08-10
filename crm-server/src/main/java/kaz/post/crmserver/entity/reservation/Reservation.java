package kaz.post.crmserver.entity.reservation;

import kaz.post.crmserver.entity.rooms.Room;
import kaz.post.crmserver.entity.users.User;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Data
@Component
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dateIssue;


    @OneToOne
    @JoinColumn(name = "roomId", insertable = false, updatable = false)
    private Room room;

    @ManyToOne
    @JoinColumn(name = "userId", insertable = false, updatable = false)
    private User user;


}
