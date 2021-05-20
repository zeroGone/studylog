package io.zerogone.user.service;

import io.zerogone.common.exception.NotExistDataException;
import io.zerogone.common.service.SearchService;
import io.zerogone.user.model.LoginRequestForm;
import io.zerogone.user.model.User;
import io.zerogone.user.model.UserDto;
import io.zerogone.user.repository.FindUserDao;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService implements SearchService<LoginRequestForm, UserDto> {
    private final FindUserDao findUserDao;
    private final ConversionService conversionService;

    public LoginService(FindUserDao findUserDao, ConversionService conversionService) {
        this.findUserDao = findUserDao;
        this.conversionService = conversionService;
    }

    @Override
    public UserDto search(LoginRequestForm loginRequestForm) {
        Optional<User> optionalUser = findUserDao.findByLoginRequest(loginRequestForm);
        User user = optionalUser.orElseThrow(new NotExistDataException("처음 방문하는 유저", loginRequestForm));
        return conversionService.convert(user, UserDto.class);
    }
}
