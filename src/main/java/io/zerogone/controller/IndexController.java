package io.zerogone.controller;

import io.zerogone.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class IndexController {
    @GetMapping("/")
    public String controlFirstAccess(@SessionAttribute(name = "user", required = false) User user) {
        if (user == null) {
            return "index";
        } else {
            return "redirect:mypage";
        }
    }
}
