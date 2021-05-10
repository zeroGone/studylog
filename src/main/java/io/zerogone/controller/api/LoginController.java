package io.zerogone.controller.api;

import io.zerogone.exception.NotExistedDataException;
import io.zerogone.model.Email;
import io.zerogone.model.dto.UserDto;
import io.zerogone.service.search.SearchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class LoginController {
    private final SearchService<Email, UserDto> searchService;

    public LoginController(SearchService<Email, UserDto> searchService) {
        this.searchService = searchService;
    }

    @PostMapping("api/login")
    public ResponseEntity<UserDto> doLogin(@RequestBody UserDto userDto, HttpSession httpSession) {
        try {
            UserDto dto = searchService.search(new Email(userDto.getEmail()));
            httpSession.setAttribute("userInfo", dto);
            return ResponseEntity.ok(dto);
        } catch (NotExistedDataException notExistedDataException) {
            httpSession.setAttribute("visitor", userDto);
            throw notExistedDataException;
        }
    }
}
