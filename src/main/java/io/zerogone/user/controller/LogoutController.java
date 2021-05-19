package io.zerogone.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class LogoutController {
    @PostMapping("api/logout")
    public ResponseEntity<Object> doLogout(HttpSession httpSession) {
        httpSession.removeAttribute("userInfo");
        return ResponseEntity.ok("logout success");
    }
}
