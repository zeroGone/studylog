package io.zerogone.blog.service;

import io.zerogone.blog.model.BlogDto;
import io.zerogone.user.model.UserDto;
import io.zerogone.blog.model.Blog;
import io.zerogone.blog.model.BlogMember;
import io.zerogone.blog.BlogDao;
import org.springframework.core.convert.converter.Converter;
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
