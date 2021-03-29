package io.zerogone.service;

import io.zerogone.exception.BlogMembersStateException;
import io.zerogone.model.entity.BlogMember;
import io.zerogone.model.entity.MemberRole;
import io.zerogone.repository.BlogMemberDao;
import io.zerogone.model.CurrentUserInfo;
import io.zerogone.model.UserDto;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BlogMemberCreateService {
    private final Log logger = LogFactory.getLog(this.getClass());
    private final BlogMemberDao blogMemberDao;

    public BlogMemberCreateService(BlogMemberDao blogMemberDao) {
        this.blogMemberDao = blogMemberDao;
    }

    @Transactional
    public void createBlogMembers(int blogId, CurrentUserInfo creator, List<UserDto> members) {
        logger.debug("-----create blog member entities start-----");
        List<BlogMember> blogMembers = new ArrayList<>();
        blogMembers.add(convertToAdminBlogMember(blogId, creator));

        if (members != null) {
            logger.debug("-----blog members are existed-----");
            validateMembers(creator.getId(), members);
            blogMembers.addAll(convertToBlogMembers(blogId, members));
        }

        blogMemberDao.save(blogMembers);
        logger.debug("-----create blog member entities end-----");
    }

    private BlogMember convertToAdminBlogMember(int blogId, CurrentUserInfo creator) {
        return new BlogMember(creator.getId(), blogId, MemberRole.ADMIN);
    }

    private void validateMembers(int adminUserId, List<UserDto> members) {
        if (members.stream().anyMatch(member -> Objects.equals(member.getId(), adminUserId))) {
            throw new BlogMembersStateException("Do not add the same user");
        }
        if (members.stream().map(UserDto::getId).distinct().count() < members.size()) {
            throw new BlogMembersStateException("Do not add the same user");
        }
    }

    private List<BlogMember> convertToBlogMembers(int blogId, List<UserDto> members) {
        return members
                .stream()
                .map(member -> new BlogMember(member.getId(), blogId, MemberRole.INVITING))
                .collect(Collectors.toList());
    }
}
