package me.ggulmool.springsecurityfilter.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Slf4j
@RestController
public class HomeController {

    @Autowired
    TestSessionDTO testSessionDTO;

    @Autowired
    SessionWire sessionWire;

    @RequestMapping("/")
    public String home(HttpSession session) {
        session.setAttribute("hello", "world");
        return (String) session.getAttribute("hello");
    }

    @RequestMapping("/hi")
    public String hi(@AuthenticationPrincipal String name) {
        return "Hello, " + name;
    }

    @RequestMapping("/se-set")
    public String seSet() {
        testSessionDTO.setName("hihi");
        testSessionDTO.setDate(new Date());
        return "testSessionDto setting";
    }

    @RequestMapping("/se-get")
    public String seGet() {
        return testSessionDTO.toString();
    }

    @RequestMapping("/store-session")
    public String store() {
        sessionWire.store("hello! session");
        return "store-session";
    }

    @RequestMapping("/get-session")
    public String get() {
        return sessionWire.toString();
    }
}
