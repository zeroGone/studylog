package io.zerogone.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LogoutController {
    @PostMapping("logout")
    public String doLogout(HttpSession httpSession) {
        httpSession.removeAttribute("user");
        return "redirect:/";
    }
}
