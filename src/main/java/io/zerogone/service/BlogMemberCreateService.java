package io.zerogone.service;

import ch.qos.logback.classic.Logger;
import io.zerogone.exception.BlogMembersStateException;
import io.zerogone.model.CurrentUserInfo;
import io.zerogone.model.UserDto;
import io.zerogone.model.entity.Blog;
import io.zerogone.model.entity.BlogMember;
import io.zerogone.model.entity.MemberRole;
import io.zerogone.model.entity.User;
import io.zerogone.repository.BlogMemberDao;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BlogMemberCreateService {
    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
    private final BlogMemberDao blogMemberDao;

    public BlogMemberCreateService(BlogMemberDao blogMemberDao) {
        this.blogMemberDao = blogMemberDao;
    }

    @Transactional
    public void createBlogMembers(Blog blog, CurrentUserInfo creator, List<UserDto> members) {
        if (members == null) {
            return;
        }

        logger.debug("-----create blog member start-----");
        validateMembers(creator.getId(), members);

        List<BlogMember> blogMembers = new ArrayList<>();
        blogMembers.add(convertToAdminBlogMember(blog, creator));
        blogMembers.addAll(convertToBlogMembers(blog, members));

        try {
            blogMemberDao.save(blogMembers);
        } catch (PersistenceException persistenceException) {
            throw new BlogMembersStateException("유효하지 않은 블로그 id나 유저 id가 포함되어 있습니다");
        }
        logger.debug("-----create blog member entities end-----");
    }

    private void validateMembers(int adminUserId, List<UserDto> members) {
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

    private BlogMember convertToAdminBlogMember(Blog blog, CurrentUserInfo creator) {
        return new BlogMember(new User(creator), blog, MemberRole.ADMIN);
    }

    private List<BlogMember> convertToBlogMembers(Blog blog, List<UserDto> members) {
        return members
                .stream()
                .map(member -> new BlogMember(new User(member), blog, MemberRole.INVITING))
                .collect(Collectors.toList());
    }
}
