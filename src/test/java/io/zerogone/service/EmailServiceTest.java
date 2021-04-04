package io.zerogone.service;

import io.zerogone.config.DatabaseConfiguration;
import io.zerogone.config.WebConfiguration;
import io.zerogone.model.UserDto;
import io.zerogone.model.entity.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfiguration.class, DatabaseConfiguration.class}, loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
public class EmailServiceTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private EmailService emailService;
    private InvitationKeyGenerator generator = new InvitationKeyGenerator();

    @Before
    public void setUp() throws Exception {
        emailService = webApplicationContext.getBean(EmailService.class);
    }

    @Test
    public void sendInvitationEmail() throws MessagingException {
        UserDto dto = new UserDto();
        dto.setEmail("dudrhs571@gmail.com");
        BlogMember blogMember = new BlogMember(new User(dto), new Blog("ㅎㅇ 테스트중", null, null), MemberRole.INVITING);

        List<BlogMemberInvitationKey> keys = new ArrayList<>();
        keys.add(new BlogMemberInvitationKey(generator.generateKey(15), blogMember));
        emailService.sendInvitationEmail(keys);
    }
}