package io.zerogone.controller.api;

import io.zerogone.model.Email;
import io.zerogone.model.dto.UserDto;
import io.zerogone.service.search.SearchService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Validated
public class UserSearchController {
    private final SearchService<Email, UserDto> searchService;

    public UserSearchController(@Qualifier("userSearchService") SearchService<Email, UserDto> searchService) {
        this.searchService = searchService;
    }

    @GetMapping("api/user")
    public UserDto handleSearchingUserByEmail(@RequestParam @Valid Email email) {
        return searchService.search(email);
    }
}
