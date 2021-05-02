package io.zerogone.service.search;

import io.zerogone.converter.Converter;
import io.zerogone.model.dto.UserWithBlogsDto;
import io.zerogone.model.entity.User;
import io.zerogone.repository.UserDao;
import org.springframework.stereotype.Service;

@Service
public class UserWithBlogListSearchService implements SearchService<String, UserWithBlogsDto> {
    private final UserDao userDao;
    private final Converter<User> converter;

    public UserWithBlogListSearchService(UserDao userDao, Converter<User> converter) {
        this.userDao = userDao;
        this.converter = converter;
    }

    @Override
    public UserWithBlogsDto search(String key) {
        User entity = userDao.findWithBlogsByEmail(key);
        return (UserWithBlogsDto) converter.convert(entity);
    }
}
