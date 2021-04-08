package io.zerogone.service;

import ch.qos.logback.classic.Logger;
import io.zerogone.exception.BlogMembersStateException;
import io.zerogone.model.UserDto;
import io.zerogone.model.UserVo;
import io.zerogone.model.entity.*;
import io.zerogone.repository.BlogMemberDao;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class BlogMemberCreateService {
    private static final int BLOG_INVITATION_KEY_LENGTH = 15;

    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
    private final BlogMemberDao blogMemberDao;
    private final EmailService emailService;
    private final InvitationKeyGenerator invitationKeyGenerator;

    public BlogMemberCreateService(BlogMemberDao blogMemberDao, EmailService emailService) {
        this.blogMemberDao = blogMemberDao;
        this.emailService = emailService;
        invitationKeyGenerator = new InvitationKeyGenerator();
    }

    @Transactional
    public void createBlogMembers(Blog blog, UserVo creator, List<UserDto> members) {
        createAdminBlogMember(blog, creator);

        if (members == null) {
            return;
        }

        validateMembers(creator.getId(), members);

        logger.info("-----Creating blog members start-----");
        List<BlogMember> blogMembersToBeInvited = createBlogMembersToBeInvited(blog, members);
        logger.info("-----Creating blog members is ended-----");

        sendBlogInvitations(blogMembersToBeInvited);
    }

    private void validateMembers(int adminUserId, List<UserDto> members) {
        if (members == null) {
            return;
        }
        logger.info("-----validate blog members-----");
        if (members.stream().anyMatch(member -> Objects.equals(member.getId(), adminUserId))) {
            throw new BlogMembersStateException("한 블로그에 같은 사람이 두 번 포함될 수 없습니다");
        }
        if (members.stream().map(UserDto::getId).distinct().count() < members.size()) {
            throw new BlogMembersStateException("한 블로그에 같은 사람이 두 번 포함될 수 없습니다");
        }
        if (members.stream().anyMatch(member -> Objects.equals(member.getId(), 0))) {
            throw new BlogMembersStateException("유효하지 않은 아이디를 가진 유저가 존재합니다");
        }
        logger.info("-----Blog Members are validated!-----");
    }

    private void createAdminBlogMember(Blog blog, UserVo creator) {
        User user = new User(creator.getId(),
                creator.getName(),
                creator.getEmail(),
                creator.getNickName(),
                creator.getImageUrl());

        try {
            blogMemberDao.save(new BlogMember(user, blog, MemberRole.ADMIN));
        } catch (PersistenceException persistenceException) {
            throw new BlogMembersStateException("유효하지 않은 블로그 id나 유저 id가 포함되어 있습니다");
        }
    }

    private List<BlogMember> createBlogMembersToBeInvited(Blog blog, List<UserDto> userDtos) {
        List<BlogMember> members = new ArrayList<>();

        for (UserDto userDto : userDtos) {
            User user = new User(userDto.getId(),
                    userDto.getName(),
                    userDto.getEmail(),
                    userDto.getNickName(),
                    userDto.getImageUrl());

            BlogInvitationKey blogInvitationKey =
                    new BlogInvitationKey(invitationKeyGenerator.generateKey(BLOG_INVITATION_KEY_LENGTH));

            BlogMember blogMember = new BlogMember(user, blog, MemberRole.INVITING);
            blogMember.setBlogInvitationKey(blogInvitationKey);

            members.add(blogMember);
        }

        try {
            blogMemberDao.save(members);
        } catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
            throw new BlogMembersStateException("유효하지 않은 블로그 id나 유저 id가 포함되어 있습니다");
        }
        return members;
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
