package io.zerogone.user.controller;

import io.zerogone.user.model.SignupForm;
import io.zerogone.user.service.SignupService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class SignupController {
    private final SignupService signupService;

    public SignupController(SignupService signupService) {
        this.signupService = signupService;
    }

    @GetMapping("signup")
    public String getSignupViewName() {
        return "signup";
    }

    @PostMapping("signup")
    @ResponseStatus(HttpStatus.CREATED)
    public String signup(@ModelAttribute @Valid SignupForm signupForm, HttpSession httpSession) {
        httpSession.removeAttribute("visitor");
        httpSession.setAttribute("user", signupService.createUser(signupForm));
        return "redirect:/mypage";
    }
}
