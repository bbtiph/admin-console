package kaz.post.crmserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @RequestMapping(value = { "/","/index" }, method = RequestMethod.GET)
    public String index(){
        return "indexkjsdhflk";
    }

    @RequestMapping(value = { "/newUser" }, method = RequestMethod.GET)
    public String register(Model model){
        return "newUser";
    }



}
