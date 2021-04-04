package io.zerogone.service;

import ch.qos.logback.classic.Logger;
import io.zerogone.exception.BlogMembersStateException;
import io.zerogone.model.entity.BlogMember;
import io.zerogone.model.entity.BlogMemberInvitationKey;
import io.zerogone.model.entity.MemberRole;
import io.zerogone.repository.BlogMemberInvitationKeyDao;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class BlogMemberInviteService {
    private static final int KEY_LENGTH = 15;

    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
    private final BlogMemberInvitationKeyDao invitationKeyDao;
    private final EmailService emailService;
    private final InvitationKeyGenerator invitationKeyGenerator;

    public BlogMemberInviteService(BlogMemberInvitationKeyDao invitationKeyDao, EmailService emailService) {
        this.invitationKeyDao = invitationKeyDao;
        this.emailService = emailService;
        invitationKeyGenerator = new InvitationKeyGenerator();
    }

    @Transactional
    public void createBlogMemberInvitationKey(List<BlogMember> members) {
        List<BlogMemberInvitationKey> invitationKeys = new ArrayList<>();
        for (BlogMember member : members) {
            if (member.getRole() == MemberRole.INVITING) {
                invitationKeys.add(new BlogMemberInvitationKey(invitationKeyGenerator.generateKey(KEY_LENGTH), member));
            }
        }

        invitationKeyDao.save(invitationKeys);

        try {
            emailService.sendInvitationEmail(invitationKeys);
        } catch (MessagingException messagingException) {
            logger.error("이메일 전송에 실패하였습니다. 원인 : " + messagingException.getMessage());
            throw new BlogMembersStateException("초대 메일 전송에 실패하였습니다!");
        }
    }
}
