package io.zerogone.service;

import io.zerogone.converter.Converter;
import io.zerogone.model.dto.BlogDto;
import io.zerogone.model.dto.UserDto;
import io.zerogone.model.entity.Blog;
import io.zerogone.model.entity.BlogMember;
import io.zerogone.repository.BlogDao;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
public class BlogInvitationAcceptenceService {
    private final BlogDao blogDao;
    private final Converter<Blog, BlogDto> converter;

    public BlogInvitationAcceptenceService(BlogDao blogDao, Converter<Blog, BlogDto> converter) {
        this.blogDao = blogDao;
        this.converter = converter;
    }

    @Transactional
    public BlogDto acceptBlogInvitation(UserDto userInfo, String key) {
        Blog entity = blogDao.findWithBlogMembersByInvitationKey(key);

        BlogMember targetMember = entity.getMembers()
                .stream()
                .filter(member -> Objects.equals(member.getUserId(), userInfo.getId()))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);

        targetMember.acceptBlogInvitation();
        return converter.convert(entity);
    }
}
