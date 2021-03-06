package io.zerogone.user.service;

import io.zerogone.common.exception.NotExistDataException;
import io.zerogone.common.service.SearchService;
import io.zerogone.user.model.Email;
import io.zerogone.blog.model.BlogDto;
import io.zerogone.user.model.UserDto;
import io.zerogone.user.model.User;
import io.zerogone.user.UserDao;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.stream.Collectors;

@Service
public class UserSearchService implements SearchService<Email, UserDto> {
    private final UserDao userDao;
    private final ConversionService conversionService;

    public UserSearchService(UserDao userDao, ConversionService conversionService) {
        this.userDao = userDao;
        this.conversionService = conversionService;
    }

    @Override
    public UserDto search(Email email) {
        try {
            User entity = userDao.findByEmail(email.get());
            UserDto dto = conversionService.convert(entity, UserDto.class);
            dto.setBlogs(entity.getBlogs()
                    .stream()
                    .map(blog -> conversionService.convert(blog, BlogDto.class))
                    .collect(Collectors.toList()));
            return dto;
        } catch (NoResultException noResultException) {
            throw new NotExistDataException("검색한 유저가 없습니다", email.get());
        }
    }
}
