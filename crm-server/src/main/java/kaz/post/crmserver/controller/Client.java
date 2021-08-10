//package kaz.post.crmserver.controller;
//
//import kaz.post.crmserver.entity.rooms.Room;
//import kaz.post.crmserver.entity.users.User;
//import kaz.post.crmserver.service.ReservationService;
//import kaz.post.crmserver.service.RoomService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//@Controller
//@RequestMapping("/users")
//public class Client {
//
//    @Autowired
//    private UserServiceOld userServiceOld;
//
//    @Autowired
//    private RoomService roomService;
//
//    @Autowired
//    private ReservationService reservationService;
//
//    @PostMapping("/signup")
//    public String newUser(@RequestParam String username,
//                          @RequestParam String fullName, @RequestParam String password, @RequestParam String phoneNumber,
//                          @RequestParam Integer age, HttpServletRequest request) {
//        User newUser = new User();
//        newUser.setUsername(username);
//        newUser.setFullName(fullName);
//        newUser.setPassword(password);
//        newUser.setPhoneNumber(phoneNumber);
//        newUser.setAge(age);
//
//        User user1 = userServiceOld.getUserbyUsername(newUser.getUsername());
//        if (user1 != null){
//            throw new RuntimeException("With this " + newUser.getUsername() + " is exist");
//        }
//
//        if (newUser.getUsername().isEmpty() || newUser.getPassword().isEmpty()){
//            throw new RuntimeException("username and password should not be empty");
//        }
//        userServiceOld.newUser(newUser);
//        HttpSession session = request.getSession();
//        session.setAttribute("user", newUser);
//
//        return "index";
//    }
//
//
//
//
//    @GetMapping("/changePassword")
//    public String changePass(Long id, String oldPass, String newPass) {
//        return userServiceOld.changePass(id, oldPass, newPass);
//    }
//
//
//    //making reservation
//    @GetMapping("/makePayment")
//    public String makePayment(Long roomId, Long userId) {
//        roomService.makeNotAvailable(roomId);
//
//        Room room = roomService.findById(roomId);
//        User user = userServiceOld.findById(userId);
//
//        reservationService.makePayment(room, user);
//        return room.toString();
//    }
//
//
//}
