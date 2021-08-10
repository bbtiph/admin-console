//package kaz.post.crmserver.controller;
//
//import kaz.post.crmserver.entity.rooms.Room;
//import kaz.post.crmserver.entity.users.User;
//import kaz.post.crmserver.service.ReservationService;
//import kaz.post.crmserver.service.RoomService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/admin")
//public class Admin {
////    @Autowired
////    private UserServiceOld userServiceOld;
////    @Autowired
//    private RoomService roomService;
//    @Autowired
//    private ReservationService reservationService;
//
//    @PostMapping
//    public void createUser(@RequestBody User user) {
//        System.out.println("UserController.createUser");
//        System.out.println("user = " + user);
//
//        userServiceOld.createUser(user);
//    }
//
//
//    @GetMapping("/create")
//    public void createUserByUsernameAndPassword(String username,
//                                                String password) {
//        User user = new User();
//        user.setUsername(username);
//        user.setPassword(password);
//        userServiceOld.createUser(user);
//    }
//
//    @ResponseBody
//    @GetMapping("/userslist")
//    public List<User> getAllUsers() {
//        return userServiceOld.getAllUsers();
//    }
//
//    @GetMapping("/allUsersList")
//    public String allUsersList(Model model){
//        model.addAttribute("users",getAllUsers());
//        return "userList";
//    }
//
//    @GetMapping("delete/{id}")
//    public void deleteUser(@PathVariable Long id) {
//        userServiceOld.deleteUser(id);
//    }
//
//    @PostMapping("/updateRoom")
//    public void updateRoom(@RequestBody Room room) {
//        roomService.updateRoom(room);
//    }
//
//    @GetMapping("/deleteRoom/{id}")
//    public void deleteRoom(@PathVariable Long id){
//        roomService.deleteRoom(id);
//    }
//
//    @PostMapping("/addRoom")
//    public void addRoom(@RequestBody Room room) {
//        roomService.addRoom(room);
//    }
//
//
//
//
//}
