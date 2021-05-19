package io.zerogone.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class SignupController {
    @GetMapping("signup")
    public String getSignupViewName(HttpSession session, Model model) {
        if (session.getAttribute("visitor") == null) {
            return "redirect:/";
        } else {
            model.addAttribute("visitor", session.getAttribute("visitor"));
            return "signup";
        }
    }
}
