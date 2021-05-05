package io.zerogone.service;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class BlogInvitationService {
    private static final String MESSAGE_ENCODING = "UTF-8";

    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
    private final JavaMailSender javaMailSender;

    public BlogInvitationService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void inviteMemberToBlog(String memberEmail, String blogName, String invitationKey) throws MessagingException {
        logger.info("-----Send blog invitation email to [" + memberEmail + "] -----");
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, MESSAGE_ENCODING);

        messageHelper.setTo(memberEmail);
        message.setSubject("[StudyLog] " + blogName + " 의 멤버로 초대합니다!");
        messageHelper.setText("<h1> " + blogName + " 의 일원으로 초대합니다!</h1>" +
                "<div> 아래 링크를 클릭하시면 " +
                blogName + " 의 확정 멤버가 됩니다~! </div>" +
                "<div> 열심히 활동 해주세요 ! </div>" +
                "<a href='http://localhost:8080/blog/accept?key=" + invitationKey + "'> 초대 수락하기 </a>", true);
        javaMailSender.send(message);
    }
}
