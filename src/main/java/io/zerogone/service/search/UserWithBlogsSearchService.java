package io.zerogone.service.search;

import io.zerogone.model.dto.BlogDto;
import io.zerogone.model.dto.UserDto;
import io.zerogone.model.entity.Blog;
import io.zerogone.model.entity.User;
import io.zerogone.repository.UserDao;
import org.hibernate.LazyInitializationException;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class UserWithBlogsSearchService implements SearchService<String, UserDto> {
    private final UserDao userDao;
    private final Converter<User, UserDto> userConverter;
    private final Converter<Blog, BlogDto> blogConverter;

    public UserWithBlogsSearchService(UserDao userDao, Converter<User, UserDto> userConverter, Converter<Blog, BlogDto> blogConverter) {
        this.userDao = userDao;
        this.userConverter = userConverter;
        this.blogConverter = blogConverter;
    }

    @Override
    public UserDto search(String email) {
        User entity = userDao.findWithBlogsByEmail(email);

        UserDto dto = userConverter.convert(entity);
        try {
            dto.setBlogs(entity.getBlogs().stream().map(blogConverter::convert).collect(Collectors.toList()));
        } catch (LazyInitializationException lazyInitializationException) {
            dto.setBlogs(new ArrayList<>());
        }

        return dto;
    }
}
