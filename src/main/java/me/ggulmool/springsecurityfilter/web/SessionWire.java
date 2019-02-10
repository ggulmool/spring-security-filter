package me.ggulmool.springsecurityfilter.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class SessionWire {

    private static final String SESSION_KEY = "SESSION_KEY";

    @Autowired
    HttpSession httpSession;

    public void store(String data) {
        httpSession.setAttribute(SESSION_KEY, data);
    }

    public String get() {
        return (String) httpSession.getAttribute(SESSION_KEY);
    }

    public String getId() {
        return httpSession.getId();
    }

    public String toString() {
        return getId() + " : " + get();
    }
}
