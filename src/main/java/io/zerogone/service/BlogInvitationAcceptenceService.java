package io.zerogone.service;

import io.zerogone.model.BlogVo;
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
    public BlogVo acceptBlogInvitation(String key) {
        BlogInvitationKey blogInvitationKey = blogInvitationKeyDao.findWithBlogMemberByValue(key);
        BlogMember blogMember = blogInvitationKey.getOwner();
        blogMember.acceptBlogInvitation();

        return new BlogVo(blogMember.getBlogId(),
                blogMember.getBlogName(),
                blogMember.getBlogIntroduce(),
                blogMember.getBlogImageUrl());
    }
}
