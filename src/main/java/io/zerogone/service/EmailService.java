package io.zerogone.service;

import io.zerogone.model.entity.Blog;
import io.zerogone.model.entity.BlogMember;
import io.zerogone.model.entity.MemberRole;
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

    public void sendInvitationEmail(List<BlogMember> members) throws MessagingException {
        for (BlogMember member : members) {
            if (member.getRole() == MemberRole.INVITING) {
                MimeMessage message = javaMailSender.createMimeMessage();
                MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, MESSAGE_ENCODING);

                messageHelper.setTo(member.getUser().getEmail());
                message.setSubject("[StudyLog] " + member.getBlog().getName() + " 의 멤버로 초대합니다!");
                messageHelper.setText(getInvitationContent(member.getBlog()), true);

                javaMailSender.send(message);
            }
        }
    }

    private String getInvitationContent(Blog blog) {
        return "<h1> " + blog.getName() + " 의 일원으로 초대합니다!</h1>" +
                "<div> 아래 링크를 클릭하시면 " +
                blog.getName() + " 의 확정 멤버가 됩니다~! </div>" +
                "<div> 열심히 활동 해주세요 ! </div>" +
                "<a href='http://localhost:8080/test'> 초대 수락하기 </a>";
    }
}
