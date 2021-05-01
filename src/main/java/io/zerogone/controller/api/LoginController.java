package io.zerogone.controller.api;

import io.zerogone.exception.NotExistedDataException;
import io.zerogone.model.dto.UserDto;
import io.zerogone.model.dto.UserWithBlogsDto;
import io.zerogone.service.search.SearchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class LoginController {
    private final SearchService<String, UserWithBlogsDto> searchService;

    public LoginController(SearchService<String, UserWithBlogsDto> searchService) {
        this.searchService = searchService;
    }

    @PostMapping("api/login")
    public ResponseEntity<UserDto> doLogin(@RequestBody UserDto userDto, HttpSession httpSession) {
        try {
            UserWithBlogsDto dto = searchService.search(userDto.getEmail());
            httpSession.setAttribute("userInfo", dto);
            return ResponseEntity.ok(dto);
        } catch (NotExistedDataException notExistedDataException) {
            httpSession.setAttribute("visitor", userDto);
            throw notExistedDataException;
        }
    }
}
