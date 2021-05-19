package io.zerogone.user.controller;

import io.zerogone.user.model.Email;
import io.zerogone.user.model.UserDto;
import io.zerogone.common.service.SearchService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Validated
public class UserSearchController {
    private final SearchService<Email, UserDto> searchService;

    public UserSearchController(SearchService<Email, UserDto> searchService) {
        this.searchService = searchService;
    }

    @GetMapping("api/user")
    public UserDto handleSearchingUserByEmail(@RequestParam @Valid Email email) {
        return searchService.search(email);
    }
}
