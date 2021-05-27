package io.zerogone.web;

import io.zerogone.domain.LoginRequestForm;
import io.zerogone.domain.SignupForm;
import io.zerogone.service.SignupService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class SignupPageController {
    private final SignupService signupService;

    public SignupPageController(SignupService signupService) {
        this.signupService = signupService;
    }

    @GetMapping("signup")
    public String getSignupViewName(@SessionAttribute LoginRequestForm visitor, Model model) {
        model.addAttribute("visitor", visitor);
        return "signup";
    }

    @PostMapping("signup")
    public ModelAndView signup(@ModelAttribute @Valid SignupForm signupForm, HttpSession httpSession, ModelAndView modelAndView) {
        try {
            httpSession.setAttribute("user", signupService.createUser(signupForm));
            httpSession.removeAttribute("visitor");
            modelAndView.setViewName("redirect:/mypage");
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            modelAndView.addObject("error", "닉네임 중복");
            modelAndView.setViewName("signup");
        }
        return modelAndView;
    }
}
