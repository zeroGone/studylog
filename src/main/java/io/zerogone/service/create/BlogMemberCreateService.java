package io.zerogone.service.create;

import ch.qos.logback.classic.Logger;
import io.zerogone.converter.Converter;
import io.zerogone.exception.BlogMembersStateException;
import io.zerogone.model.dto.BlogMemberDto;
import io.zerogone.model.dto.DataTransferObject;
import io.zerogone.model.entity.BlogInvitationKey;
import io.zerogone.model.entity.BlogMember;
import io.zerogone.model.entity.MemberRole;
import io.zerogone.repository.BlogInvitationKeyDao;
import io.zerogone.repository.BlogMemberDao;
import io.zerogone.service.InvitationKeyGenerator;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

@Service
public class BlogMemberCreateService implements CreateService {
    private static final String MESSAGE_ENCODING = "UTF-8";
    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    private final BlogMemberDao blogMemberDao;
    private final BlogInvitationKeyDao blogInvitationKeyDao;
    private final Converter<BlogMember> converter;
    private final JavaMailSender javaMailSender;
    private final InvitationKeyGenerator keyGenerator;

    public BlogMemberCreateService(BlogMemberDao blogMemberDao, BlogInvitationKeyDao blogInvitationKeyDao, Converter<BlogMember> converter, JavaMailSender javaMailSender) {
        this.blogMemberDao = blogMemberDao;
        this.blogInvitationKeyDao = blogInvitationKeyDao;
        this.converter = converter;
        this.javaMailSender = javaMailSender;
        this.keyGenerator = new InvitationKeyGenerator();
    }

    @Transactional
    @Override
    public DataTransferObject create(DataTransferObject dto) {
        BlogMember entity = saveEntity((BlogMemberDto) dto);

        if (entity.getRole() == MemberRole.INVITING) {
            BlogInvitationKey blogInvitationKey = new BlogInvitationKey(keyGenerator.generateKey(), entity);
            blogInvitationKeyDao.save(blogInvitationKey);
            try {
                sendInvitationEmail(entity.getBlogName(), entity.getEmail(), blogInvitationKey.getValue());
            } catch (MessagingException messagingException) {
                logger.error("이메일 전송에 실패하였습니다. 원인 : " + messagingException.getMessage());
                throw new BlogMembersStateException("초대 메일 전송에 실패하였습니다!");
            }
        }
        return converter.convert(entity);
    }

    private BlogMember saveEntity(BlogMemberDto blogMemberDto) {
        BlogMember entity = converter.convert(blogMemberDto);
        try {
            blogMemberDao.save(entity);
        } catch (PersistenceException persistenceException) {
            throw new BlogMembersStateException("유효하지 않은 블로그 id나 유저 id가 포함되어 있습니다");
        }
        return entity;
    }

    public void sendInvitationEmail(String blogName, String userEmail, String key) throws MessagingException {
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
