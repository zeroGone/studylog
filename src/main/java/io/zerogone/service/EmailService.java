package io.zerogone.service;

import io.zerogone.model.entity.BlogMemberInvitationKey;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
public class EmailService {
    private static final String MESSAGE_ENCODING = "UTF-8";

    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendInvitationEmail(List<BlogMemberInvitationKey> invitationKeys) throws MessagingException {
        for (BlogMemberInvitationKey invitationKey : invitationKeys) {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, MESSAGE_ENCODING);

            messageHelper.setTo(invitationKey.getUserEmail());
            message.setSubject("[StudyLog] " + invitationKey.getBlogName() + " 의 멤버로 초대합니다!");
            messageHelper.setText(getInvitationContent(invitationKey.getBlogName(), invitationKey.getValue()), true);
            javaMailSender.send(message);
        }
    }

    private String getInvitationContent(String blogName, String key) {
        return "<h1> " + blogName + " 의 일원으로 초대합니다!</h1>" +
                "<div> 아래 링크를 클릭하시면 " +
                blogName + " 의 확정 멤버가 됩니다~! </div>" +
                "<div> 열심히 활동 해주세요 ! </div>" +
                "<a href='http://localhost:8080/accept-blog?key=" + key + "'> 초대 수락하기 </a>";
    }
}
