package io.zerogone.controller;

import ch.qos.logback.classic.Logger;
import io.zerogone.exception.UniquePropertyException;
import io.zerogone.model.CurrentUserInfo;
import io.zerogone.model.ErrorResponse;
import io.zerogone.model.UserCreateDto;
import io.zerogone.model.UserVo;
import io.zerogone.service.UserCreateService;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class SignupController {
    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
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
    public ResponseEntity<Object> signUp(@ModelAttribute UserCreateDto userCreateDto,
                                         HttpSession httpSession) {
        logger.info("-----create user start-----");
        logger.debug(String.format("received user data { name : %s, email : %s, nickName : %s }"
                , userCreateDto.getName(), userCreateDto.getEmail(), userCreateDto.getNickName()));

        try {
            UserVo userVo = userCreateService.createUser(userCreateDto);
            httpSession.setAttribute("userInfo", new CurrentUserInfo(userVo));
            httpSession.removeAttribute("visitor");
            return new ResponseEntity<>(userVo, HttpStatus.CREATED);
        } catch (UniquePropertyException uniquePropertyException) {
            return ResponseEntity.ok(new ErrorResponse(uniquePropertyException.getMessage()));
        }
    }
}
