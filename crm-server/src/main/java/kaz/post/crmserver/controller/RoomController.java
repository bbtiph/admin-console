//package kaz.post.crmserver.controller;
//
//import kaz.post.crmserver.entity.rooms.Room;
//import kaz.post.crmserver.service.ReservationService;
//import kaz.post.crmserver.service.RoomService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/rooms")
//public class RoomController {
////    @Autowired
////    private UserServiceOld userServiceOld;
//    @Autowired
//    private RoomService roomService;
//    @Autowired
//    private ReservationService reservationService;
//
//    @GetMapping("/getRoom")
//    public String getRoom(@RequestParam String rid, Model model) {
//        Long id = Long.parseLong(rid);
//        Room room = roomService.findById(id);
//        model.addAttribute("Room", room);
//        return "roomDetail";
//    }
//
//    @GetMapping("/availableRooms")
//    public List<Room> availableRooms() {
//        return roomService.showAvailableRooms();
//    }
//
//    @GetMapping("/allRooms")
//    public String allRooms(Model model){
//        model.addAttribute("ph",availableRooms());
//        return "rooms";
//    }
//
//
//}
