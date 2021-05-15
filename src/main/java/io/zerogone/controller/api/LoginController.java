package io.zerogone.controller.api;

import io.zerogone.exception.NotExistDataException;
import io.zerogone.model.Email;
import io.zerogone.model.dto.UserDto;
import io.zerogone.service.search.SearchService;
import io.zerogone.validator.Login;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;

@RestController
public class LoginController {
    private final SearchService<Email, UserDto> searchService;

    public LoginController(SearchService<Email, UserDto> searchService) {
        this.searchService = searchService;
    }

    @PostMapping("api/login")
    public UserDto doLogin(@RequestBody @Validated(Login.class) UserDto userDto, HttpSession httpSession) {
        try {
            UserDto dto = searchService.search(new Email(userDto.getEmail()));
            httpSession.setAttribute("userInfo", dto);
            return dto;
        } catch (NoResultException noResultException) {
            httpSession.setAttribute("visitor", userDto);
            throw new NotExistDataException("로그인 실패", userDto);
        }
    }
}
