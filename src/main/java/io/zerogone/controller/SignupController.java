package io.zerogone.controller;

import io.zerogone.model.ErrorResponse;
import io.zerogone.user.model.UserDto;
import io.zerogone.user.model.UserVo;
import io.zerogone.user.service.UserCreateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpSession;

@Controller
public class SignupController {
    private final UserCreateService userCreateService;

    public SignupController(UserCreateService userCreateService) {
        this.userCreateService = userCreateService;
    }

    @GetMapping("signup")
    public String getSignupViewName(HttpSession session, Model model) {
        if (session.getAttribute("visitor") == null) {
            return "redirect:/";
        } else {
            model.addAttribute("visitor", session.getAttribute("visitor"));
            return "signup";
        }
    }

    @PostMapping("signup")
    public ResponseEntity<Object> handleSignupApi(@RequestBody UserDto userDto, HttpSession httpSession) {
        try {
            UserVo userVo = userCreateService.createUser(userDto);
            httpSession.setAttribute("userInfo", userVo);
            httpSession.removeAttribute("visitor");
            return new ResponseEntity<>(userVo, HttpStatus.CREATED);
        } catch (PersistenceException persistenceException) {
            return new ResponseEntity<>(new ErrorResponse("NickName or email is Duplicated!"), HttpStatus.BAD_REQUEST);
        }
    }
}
