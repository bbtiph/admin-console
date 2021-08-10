package kaz.post.crmserver.entity.rooms;

import kaz.post.crmserver.entity.reservation.Reservation;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

@Data
@Entity(name="rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean isAvailable;
    private String ShortDesc;
    private String imgLink;
    private RoomType type;
    private int price;

    @Getter(AccessLevel.NONE)
    @OneToOne(mappedBy = "room", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Reservation reservation;
}
