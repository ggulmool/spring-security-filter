package me.ggulmool.springsecurityfilter.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/register")
    public String register() {
        return "register";
    }
}
