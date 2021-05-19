package io.zerogone.user.service;

import io.zerogone.common.exception.NotExistDataException;
import io.zerogone.common.service.SearchService;
import io.zerogone.user.model.LoginRequest;
import io.zerogone.user.model.User;
import io.zerogone.user.model.UserDto;
import io.zerogone.user.repository.FindUserDao;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService implements SearchService<LoginRequest, UserDto> {
    private final FindUserDao findUserDao;
    private final ConversionService conversionService;

    public LoginService(FindUserDao findUserDao, ConversionService conversionService) {
        this.findUserDao = findUserDao;
        this.conversionService = conversionService;
    }

    @Override
    public UserDto search(LoginRequest loginRequest) {
        Optional<User> optionalUser = findUserDao.findByLoginRequest(loginRequest);
        User user = optionalUser.orElseThrow(new NotExistDataException("처음 방문하는 유저", loginRequest));
        return conversionService.convert(user, UserDto.class);
    }
}
