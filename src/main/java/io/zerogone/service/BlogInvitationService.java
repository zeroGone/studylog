package io.zerogone.service;

import ch.qos.logback.classic.Logger;
import io.zerogone.exception.BlogMembersStateException;
import io.zerogone.model.entity.BlogInvitationKey;
import io.zerogone.model.entity.BlogMember;
import io.zerogone.repository.BlogInvitationKeyDao;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class BlogInvitationService {
    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    private final BlogInvitationKeyDao blogInvitationKeyDao;
    private final EmailService emailService;
    private final InvitationKeyGenerator invitationKeyGenerator;

    public BlogInvitationService(BlogInvitationKeyDao blogInvitationKeyDao, EmailService emailService) {
        this.blogInvitationKeyDao = blogInvitationKeyDao;
        this.emailService = emailService;
        this.invitationKeyGenerator = new InvitationKeyGenerator();
    }

    @Transactional
    public void inviteBlogMembers(List<BlogMember> blogMembers) {
        createBlogInvitationKeys(blogMembers);
        sendBlogInvitations(blogMembers);
    }

    private void createBlogInvitationKeys(List<BlogMember> blogMembers) {
        blogMembers.forEach(blogMember -> {
            BlogInvitationKey blogInvitationKey =
                    new BlogInvitationKey(invitationKeyGenerator.generateKey(), blogMember);
            blogInvitationKeyDao.save(blogInvitationKey);
            blogMember.setBlogInvitationKey(blogInvitationKey);
        });
    }

    private void sendBlogInvitations(List<BlogMember> members) {
        try {
            emailService.sendInvitationEmail(members);
        } catch (MessagingException messagingException) {
            logger.error("이메일 전송에 실패하였습니다. 원인 : " + messagingException.getMessage());
            throw new BlogMembersStateException("초대 메일 전송에 실패하였습니다!");
        }
    }
}
