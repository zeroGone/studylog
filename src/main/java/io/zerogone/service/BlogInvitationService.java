package io.zerogone.service;

import ch.qos.logback.classic.Logger;
import io.zerogone.exception.BlogMembersStateException;
import io.zerogone.model.entity.BlogInvitationKey;
import io.zerogone.model.entity.BlogMember;
import io.zerogone.model.entity.MemberRole;
import io.zerogone.repository.BlogInvitationKeyDao;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class BlogInvitationService {
    private static final String MESSAGE_ENCODING = "UTF-8";

    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
    private final BlogInvitationKeyDao blogInvitationKeyDao;
    private final JavaMailSender javaMailSender;
    private final InvitationKeyGenerator invitationKeyGenerator;

    public BlogInvitationService(BlogInvitationKeyDao blogInvitationKeyDao, JavaMailSender javaMailSender) {
        this.blogInvitationKeyDao = blogInvitationKeyDao;
        this.javaMailSender = javaMailSender;
        invitationKeyGenerator = new InvitationKeyGenerator();
    }

    @Transactional
    public void inviteBlog(List<BlogMember> blogMemberList) {
        for (BlogMember member : blogMemberList) {
            if (member.getRole() == MemberRole.INVITING) {
                BlogInvitationKey blogInvitationKey = new BlogInvitationKey(invitationKeyGenerator.generateKey(), member);
                blogInvitationKeyDao.save(blogInvitationKey);
                try {
                    sendInvitationEmail(member.getBlogName(), member.getEmail(), blogInvitationKey.getValue());
                } catch (MessagingException messagingException) {
                    logger.error("이메일 전송에 실패하였습니다. 원인 : " + messagingException.getMessage());
                    throw new BlogMembersStateException("초대 메일 전송에 실패하였습니다!");
                }
            }
        }
    }

    private void sendInvitationEmail(String blogName, String userEmail, String key) throws MessagingException {
        logger.info("-----Send blog invitation email to [" + userEmail + "] -----");
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, MESSAGE_ENCODING);

        messageHelper.setTo(userEmail);
        message.setSubject("[StudyLog] " + blogName + " 의 멤버로 초대합니다!");
        messageHelper.setText("<h1> " + blogName + " 의 일원으로 초대합니다!</h1>" +
                "<div> 아래 링크를 클릭하시면 " +
                blogName + " 의 확정 멤버가 됩니다~! </div>" +
                "<div> 열심히 활동 해주세요 ! </div>" +
                "<a href='http://localhost:8080/blog/accept?key=" + key + "'> 초대 수락하기 </a>", true);
        javaMailSender.send(message);
    }
}
