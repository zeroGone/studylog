package io.zerogone.service.search;

import io.zerogone.model.dto.BlogWithMembersDto;
import io.zerogone.model.dto.UserDto;
import io.zerogone.model.entity.Blog;
import io.zerogone.repository.BlogDao;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class BlogWithMembersSearchService implements SearchService<String, BlogWithMembersDto> {
    private final BlogDao blogDao;

    public BlogWithMembersSearchService(BlogDao blogDao) {
        this.blogDao = blogDao;
    }

    @Override
    public BlogWithMembersDto search(String key) {
        Blog entity = blogDao.findWithBlogMembersByName(key);
        BlogWithMembersDto dto = new BlogWithMembersDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setIntroduce(entity.getIntroduce());
        dto.setImageUrl(entity.getImageUrl());
        dto.setMembers(entity.getMembers()
                .stream()
                .map(user -> {
                    UserDto userDto = new UserDto();
                    userDto.setId(user.getId());
                    userDto.setName(user.getName());
                    userDto.setEmail(user.getEmail());
                    userDto.setNickName(user.getNickName());
                    userDto.setImageUrl(user.getImageUrl());
                    return userDto;
                }).collect(Collectors.toList()));
        return dto;
    }
}
