package io.zerogone.user.controller;

import io.zerogone.user.model.NoResult;
import io.zerogone.user.service.UserSearchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.NoResultException;

@Controller
public class UserSearchController {
    private final UserSearchService userSearchService;

    public UserSearchController(UserSearchService userSearchService) {
        this.userSearchService = userSearchService;
    }

    @GetMapping("api/user")
    public ResponseEntity<Object> handleUserSearchApi(@RequestParam String email) {
        try {
            return new ResponseEntity<>(userSearchService.getUserHasEmail(email), HttpStatus.OK);
        } catch (NoResultException noResultException) {
            return new ResponseEntity<>(new NoResult(), HttpStatus.NOT_FOUND);
        }
    }
}
