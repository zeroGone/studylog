package io.zerogone.service.search;

import io.zerogone.converter.Converter;
import io.zerogone.model.dto.BlogDto;
import io.zerogone.model.dto.BlogMemberDto;
import io.zerogone.model.entity.Blog;
import io.zerogone.model.entity.BlogMember;
import io.zerogone.repository.BlogDao;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

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

    private Set<BlogMemberDto> convertMembers(Set<BlogMember> members) {
        Set<BlogMemberDto> memberDtos = new HashSet<>();
        for (BlogMember member : members) {
            BlogMemberDto memberDto = new BlogMemberDto();
            memberDto.setId(member.getUserId());
            memberDto.setName(member.getName());
            memberDto.setEmail(member.getEmail());
            memberDto.setNickName(member.getNickName());
            memberDto.setImageUrl(member.getImageUrl());
            memberDto.setRole(member.getRole());
            memberDtos.add(memberDto);
        }
        return memberDtos;
    }
}
