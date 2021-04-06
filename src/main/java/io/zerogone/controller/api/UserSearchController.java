package io.zerogone.controller.api;

import io.zerogone.model.UserVo;
import io.zerogone.service.UserSearchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserSearchController {
    private final UserSearchService userSearchService;

    public UserSearchController(UserSearchService userSearchService) {
        this.userSearchService = userSearchService;
    }

    @GetMapping("api/user")
    public ResponseEntity<UserVo> handleUserSearchApi(@RequestParam String email) {
        return ResponseEntity.ok(userSearchService.getUserByEmail(email));
    }
}
