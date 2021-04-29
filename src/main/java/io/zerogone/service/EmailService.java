package io.zerogone.service;

import ch.qos.logback.classic.Logger;
import io.zerogone.model.entity.BlogInvitationKey;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
public class EmailService {
    private static final String MESSAGE_ENCODING = "UTF-8";

    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendInvitationEmail(List<BlogInvitationKey> blogInvitationKeys) throws MessagingException {
        for (BlogInvitationKey key : blogInvitationKeys) {
            logger.info("-----Send blog invitation email to [" + key.getOwnerEmail() + "] -----");
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, MESSAGE_ENCODING);

            messageHelper.setTo(key.getOwnerEmail());
            message.setSubject("[StudyLog] " + key.getBlogName() + " 의 멤버로 초대합니다!");
            messageHelper.setText("<h1> " + key.getBlogName() + " 의 일원으로 초대합니다!</h1>" +
                    "<div> 아래 링크를 클릭하시면 " +
                    key.getBlogName() + " 의 확정 멤버가 됩니다~! </div>" +
                    "<div> 열심히 활동 해주세요 ! </div>" +
                    "<a href='http://localhost:8080/blog/accept?key=" + key.getValue() + "'> 초대 수락하기 </a>", true);
            javaMailSender.send(message);
        }
    }
}
