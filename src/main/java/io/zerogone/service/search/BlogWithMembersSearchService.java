package io.zerogone.service.search;

import io.zerogone.converter.Converter;
import io.zerogone.model.dto.BlogDto;
import io.zerogone.model.dto.BlogMemberDto;
import io.zerogone.model.entity.Blog;
import io.zerogone.model.entity.User;
import io.zerogone.repository.BlogDao;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogWithMembersSearchService implements SearchService<String, BlogDto> {
    private final BlogDao blogDao;
    private final Converter<Blog, BlogDto> converter;

    public BlogWithMembersSearchService(BlogDao blogDao, Converter<Blog, BlogDto> converter) {
        this.blogDao = blogDao;
        this.converter = converter;
    }

    @Override
    public BlogDto search(String name) {
        Blog entity = blogDao.findWithBlogMembersByName(name);

        BlogDto dto = converter.convert(entity);
        dto.setMembers(convertMembers(entity.getMembers()));
        return dto;
    }

    private BlogDto convertEntity(Blog entity) {
        BlogDto dto = new BlogDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setIntroduce(entity.getIntroduce());
        dto.setImageUrl(entity.getImageUrl());
        return dto;
    }

    private List<BlogMemberDto> convertMembers(List<User> members) {
        List<BlogMemberDto> memberDtos = new ArrayList<>();
        for (User user : members) {
            BlogMemberDto memberDto = new BlogMemberDto();
            memberDto.setId(user.getId());
            memberDto.setName(user.getName());
            memberDto.setEmail(user.getEmail());
            memberDto.setNickName(user.getNickName());
            memberDto.setImageUrl(user.getImageUrl());
            memberDtos.add(memberDto);
        }
        return memberDtos;
    }
}
