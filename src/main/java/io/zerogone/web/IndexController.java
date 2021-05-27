package io.zerogone.web;

import io.zerogone.domain.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class IndexController {
    @GetMapping("/")
    public String getIndexViewName(@SessionAttribute(required = false) User user) {
        if (user == null) {
            return "index";
        } else {
            return "redirect:/mypage";
        }
    }
}
