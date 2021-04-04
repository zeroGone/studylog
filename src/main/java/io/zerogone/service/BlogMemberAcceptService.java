package io.zerogone.service;

import io.zerogone.model.BlogVo;
import io.zerogone.model.entity.BlogMember;
import io.zerogone.model.entity.BlogMemberInvitationKey;
import io.zerogone.repository.BlogMemberDao;
import io.zerogone.repository.BlogMemberInvitationKeyDao;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class BlogMemberAcceptService {
    private final BlogMemberInvitationKeyDao blogMemberInvitationKeyDao;
    private final BlogMemberDao blogMemberDao;

    public BlogMemberAcceptService(BlogMemberInvitationKeyDao blogMemberInvitationKeyDao, BlogMemberDao blogMemberDao) {
        this.blogMemberInvitationKeyDao = blogMemberInvitationKeyDao;
        this.blogMemberDao = blogMemberDao;
    }

    @Transactional
    public BlogVo acceptBlogInvitation(String key) {
        BlogMemberInvitationKey invitationKey = blogMemberInvitationKeyDao.findBlogMemberInvitationKeyByValue(key);

        BlogMember target = invitationKey.getOwner();
        target.acceptBlogMember();
        blogMemberDao.update(target);

        return new BlogVo(target.getBlog());
    }
}
