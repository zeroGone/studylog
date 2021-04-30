package io.zerogone.service;

import ch.qos.logback.classic.Logger;
import io.zerogone.exception.BlogMembersStateException;
import io.zerogone.model.UserDto;
import io.zerogone.model.vo.UserVo;
import io.zerogone.model.entity.Blog;
import io.zerogone.model.entity.BlogMember;
import io.zerogone.model.entity.MemberRole;
import io.zerogone.model.entity.User;
import io.zerogone.repository.BlogMemberDao;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BlogMemberCreateService {
    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
    private final BlogMemberDao blogMemberDao;
    private final BlogInvitationService blogInvitationService;

    public BlogMemberCreateService(BlogMemberDao blogMemberDao, BlogInvitationService blogInvitationService) {
        this.blogMemberDao = blogMemberDao;
        this.blogInvitationService = blogInvitationService;
    }

    @Transactional
    public void createBlogMembers(Blog blog, UserVo creator, List<UserDto> members) {
        createAdminBlogMember(blog, creator);
        logger.info("-----Blog admin is created -----");

        if (members != null) {
            logger.info("-----Creating blog members start-----");
            validateMembers(creator.getId(), members);
            List<BlogMember> blogMembersToBeInvited = createBlogMembersToBeInvited(blog, members);
            blogInvitationService.inviteBlogMembers(blogMembersToBeInvited);
            logger.info("-----Creating blog members is ended-----");
        }
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
        List<BlogMember> members = userDtos
                .stream()
                .map(userDto -> new User(
                        userDto.getId(),
                        userDto.getName(),
                        userDto.getEmail(),
                        userDto.getNickName(),
                        userDto.getImageUrl()))
                .map(user -> new BlogMember(user, blog))
                .collect(Collectors.toList());

        try {
            blogMemberDao.save(members);
        } catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
            throw new BlogMembersStateException("유효하지 않은 블로그 id나 유저 id가 포함되어 있습니다");
        }
        return members;
    }
}
