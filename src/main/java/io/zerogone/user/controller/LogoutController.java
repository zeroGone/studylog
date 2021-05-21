package io.zerogone.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LogoutController {
    @PostMapping("logout")
    public String doLogout(HttpSession httpSession) {
        httpSession.removeAttribute("userInfo");
        return "redirect:/";
    }
}
