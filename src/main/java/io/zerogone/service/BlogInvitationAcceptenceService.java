package io.zerogone.service;

import io.zerogone.model.BlogVo;
import io.zerogone.model.entity.BlogMember;
import io.zerogone.repository.BlogMemberDao;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class BlogInvitationAcceptenceService {
    private final BlogMemberDao blogMemberDao;

    public BlogInvitationAcceptenceService(BlogMemberDao blogMemberDao) {
        this.blogMemberDao = blogMemberDao;
    }

    @Transactional
    public BlogVo acceptBlogInvitation(String key) {
        BlogMember blogMember = blogMemberDao.findByBlogInviationKeyValue(key);
        blogMember.acceptBlogInvitation();
        blogMemberDao.update(blogMember);

        return new BlogVo(blogMember.getBlogId(),
                blogMember.getBlogName(),
                blogMember.getBlogIntroduce(),
                blogMember.getBlogImageUrl(),
                blogMember.getBlogCreateDateTime(),
                blogMember.getBlogUpdateDateTime());
    }
}
