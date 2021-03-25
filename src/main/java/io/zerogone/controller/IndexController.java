package io.zerogone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {
    @GetMapping("/")
    public String getIndexViewName(HttpSession session) {
        if (session.getAttribute("userInfo") == null) {
            return "index";
        } else {
            return "redirect:/mypage";
        }
    }
}
