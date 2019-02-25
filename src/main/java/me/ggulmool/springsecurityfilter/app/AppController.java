package me.ggulmool.springsecurityfilter.app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/app")
public class AppController {

    @RequestMapping("/mypage")
    public String mypage() {
        return "app/mypage";
    }
}
