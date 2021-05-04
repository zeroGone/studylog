package io.zerogone.service.create;

import ch.qos.logback.classic.Logger;
import io.zerogone.exception.BlogMembersStateException;
import io.zerogone.model.dto.BlogMemberDto;
import io.zerogone.model.entity.Blog;
import io.zerogone.model.entity.BlogMember;
import io.zerogone.model.entity.User;
import io.zerogone.repository.BlogMemberDao;
import io.zerogone.service.BlogInvitationService;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BlogMemberCreateService implements CreateService<List<BlogMemberDto>> {
    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    private final BlogMemberDao blogMemberDao;
    private final BlogInvitationService blogInvitationService;

    public BlogMemberCreateService(BlogMemberDao blogMemberDao, BlogInvitationService blogInvitationService) {
        this.blogMemberDao = blogMemberDao;
        this.blogInvitationService = blogInvitationService;
    }

    @Transactional
    @Override
    public List<BlogMemberDto> create(List<BlogMemberDto> dto) {
        validateMembers(dto);
        List<BlogMember> entities = convert(dto);
        try {
            entities.forEach(blogMemberDao::save);
        } catch (PersistenceException persistenceException) {
            throw new BlogMembersStateException("유효하지 않은 블로그 id나 유저 id가 포함되어 있습니다");
        }
        blogInvitationService.inviteBlog(entities);
        return dto;
    }

    private void validateMembers(List<BlogMemberDto> members) {
        logger.info("-----validate blog members-----");
        if (members.stream().map(BlogMemberDto::getId).distinct().count() < members.size()) {
            throw new BlogMembersStateException("한 블로그에 같은 사람이 두 번 포함될 수 없습니다");
        }
        if (members.stream().anyMatch(member -> Objects.equals(member.getId(), 0))) {
            throw new BlogMembersStateException("유효하지 않은 아이디를 가진 유저가 존재합니다");
        }
        logger.info("-----Blog Members are validated!-----");
    }

    private List<BlogMember> convert(List<BlogMemberDto> dtos) {
        return dtos.stream().map(dto -> {
            Blog blog = new Blog(dto.getBlogId(), dto.getBlogName(), dto.getBlogIntroduce(), dto.getBlogImageUrl());
            User user = new User(dto.getId(), dto.getName(), dto.getEmail(), dto.getNickName(), dto.getImageUrl());
            return new BlogMember(user, blog, dto.getRole());
        }).collect(Collectors.toList());
    }
}
