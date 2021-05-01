package io.zerogone.service.search;

import io.zerogone.model.dto.BlogDto;
import io.zerogone.model.dto.UserWithBlogsDto;
import io.zerogone.model.entity.User;
import io.zerogone.repository.UserDao;
import org.hibernate.LazyInitializationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class UserWithBlogListSearchService implements SearchService<String, UserWithBlogsDto> {
    private final UserDao userDao;

    public UserWithBlogListSearchService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserWithBlogsDto search(String key) {
        User entity = userDao.findWithBlogsByEmail(key);
        UserWithBlogsDto dto = new UserWithBlogsDto();
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setName(entity.getName());
        dto.setNickName(entity.getNickName());
        dto.setImageUrl(entity.getImageUrl());
        try {
            dto.setBlogs(entity.getBlogs()
                    .stream()
                    .map(blog -> {
                        BlogDto blogDto = new BlogDto();
                        blogDto.setId(blog.getId());
                        blogDto.setIntroduce(blog.getIntroduce());
                        blogDto.setName(blog.getName());
                        blogDto.setImageUrl(blog.getImageUrl());
                        return blogDto;
                    })
                    .collect(Collectors.toList()));
        } catch (LazyInitializationException lazyInitializationException) {
            dto.setBlogs(new ArrayList<>());
        }
        return dto;
    }
}
