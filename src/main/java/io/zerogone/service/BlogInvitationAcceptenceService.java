package io.zerogone.service;

import io.zerogone.model.dto.BlogDto;
import io.zerogone.model.entity.BlogInvitationKey;
import io.zerogone.model.entity.BlogMember;
import io.zerogone.repository.BlogInvitationKeyDao;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class BlogInvitationAcceptenceService {
    private final BlogInvitationKeyDao blogInvitationKeyDao;

    public BlogInvitationAcceptenceService(BlogInvitationKeyDao blogInvitationKeyDao) {
        this.blogInvitationKeyDao = blogInvitationKeyDao;
    }

    @Transactional
    public BlogDto acceptBlogInvitation(String key) {
        BlogInvitationKey blogInvitationKey = blogInvitationKeyDao.findWithBlogMemberByValue(key);
        BlogMember blogMember = blogInvitationKey.getOwner();
        blogMember.acceptBlogInvitation();

        BlogDto blogDto = new BlogDto();
        blogDto.setId(blogMember.getBlogId());
        blogDto.setName(blogMember.getBlogName());
        blogDto.setIntroduce(blogMember.getBlogIntroduce());
        blogDto.setImageUrl(blogMember.getBlogImageUrl());
        return blogDto;
    }
}
