package io.zerogone.user.controller;

import io.zerogone.user.model.CurrentUserInfo;
import io.zerogone.user.service.UserSearchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.persistence.NoResultException;

@RestController
public class UserApiController {
    private final UserSearchService userSearchService;

    public UserApiController(UserSearchService userSearchService) {
        this.userSearchService = userSearchService;
    }

    @GetMapping("api/user")
    public ResponseEntity<Object> handleUserSearchApi(@SessionAttribute CurrentUserInfo userInfo,
                                                      @RequestParam String email) {
        if (email.equals(userInfo.getEmail())) {
            return new ResponseEntity<>(new Error("자기 자신을 멤버로 초대할 수 없습니다"), HttpStatus.BAD_REQUEST);
        }

        try {
            return new ResponseEntity<>(userSearchService.getUserHasEmail(email), HttpStatus.OK);
        } catch (NoResultException noResultException) {
            return new ResponseEntity<>(new Error("검색 결과 없음"), HttpStatus.NOT_FOUND);
        }
    }
}
