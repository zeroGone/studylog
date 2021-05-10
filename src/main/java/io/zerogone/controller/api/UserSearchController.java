package io.zerogone.controller.api;

import io.zerogone.model.Email;
import io.zerogone.model.dto.UserDto;
import io.zerogone.service.search.SearchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserSearchController {
    private final SearchService<Email, UserDto> searchService;

    public UserSearchController(SearchService<Email, UserDto> searchService) {
        this.searchService = searchService;
    }

    @GetMapping("api/user")
    public ResponseEntity<UserDto> handleUserSearchApi(@RequestParam Email email) {
        return ResponseEntity.ok(searchService.search(email));
    }
}
