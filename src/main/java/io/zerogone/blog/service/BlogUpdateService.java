package io.zerogone.blog.service;

import io.zerogone.blog.model.BlogDto;
import io.zerogone.blog.model.Blog;
import io.zerogone.blog.model.BlogMember;
import io.zerogone.model.entity.MemberRole;
import io.zerogone.user.model.User;
import io.zerogone.blog.BlogDao;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogUpdateService {
    private final BlogDao blogDao;
    private final BlogInvitationService blogInvitationService;

    public BlogUpdateService(BlogDao blogDao, BlogInvitationService blogInvitationService) {
        this.blogDao = blogDao;
        this.blogInvitationService = blogInvitationService;
    }

    @Transactional
    public BlogDto updateBlog(BlogDto dto) {
        Blog entity = blogDao.findById(dto.getId());
        entity.setIntroduce(dto.getIntroduce());
        entity.setImageUrl(dto.getImageUrl());

        List<BlogMember> newMembers = getNewMembers(entity, dto);
        newMembers.forEach(member -> {
            entity.addMember(member);
            blogInvitationService.inviteMemberToBlog(member.getEmail(), entity.getName(), entity.getInvitationKey());
        });
        return dto;
    }

    private List<BlogMember> getNewMembers(Blog entity, BlogDto dto) {
        List<BlogMember> dtoMembers = dto.getMembers()
                .stream()
                .map(memberDto -> {
                    User user = new User(memberDto.getId(), memberDto.getName(), memberDto.getEmail(), memberDto.getNickName(), memberDto.getImageUrl());
                    return new BlogMember(user, new Blog(entity.getId(), null, null, null, null), MemberRole.MEMBER);
                })
                .collect(Collectors.toList());

        dtoMembers.removeAll(entity.getMembers());
        return dtoMembers;
    }
}
