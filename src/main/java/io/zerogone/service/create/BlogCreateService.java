package io.zerogone.service.create;

import ch.qos.logback.classic.Logger;
import io.zerogone.converter.Converter;
import io.zerogone.exception.BlogMembersStateException;
import io.zerogone.exception.NotNullPropertyException;
import io.zerogone.exception.UniquePropertyException;
import io.zerogone.model.dto.BlogDto;
import io.zerogone.model.dto.BlogMemberDto;
import io.zerogone.model.entity.Blog;
import io.zerogone.model.entity.BlogMember;
import io.zerogone.model.entity.MemberRole;
import io.zerogone.model.entity.User;
import io.zerogone.repository.BlogDao;
import io.zerogone.service.BlogInvitationService;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BlogCreateService implements CreateService<BlogDto> {
    private static final String BLOG_DEFAULT_IMAGE_URL = "/img/blog-default.png";

    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
    private final BlogDao blogDao;
    private final BlogInvitationService blogInvitationService;
    private final Converter<Blog, BlogDto> entityConverter;

    public BlogCreateService(BlogDao blogDao, BlogInvitationService blogInvitationService, Converter<Blog, BlogDto> entityConverter) {
        this.blogDao = blogDao;
        this.blogInvitationService = blogInvitationService;
        this.entityConverter = entityConverter;
    }

    @Transactional
    @Override
    public BlogDto create(BlogDto dto) {
        validate(dto);
        Blog entity = initializeEntity(dto);
        try {
            blogDao.save(entity);
        } catch (PersistenceException persistenceException) {
            logger.error("Blog name is duplicated ! " + persistenceException.getMessage());
            throw new UniquePropertyException("블로그 이름이 중복되었습니다");
        }
        inviteMembers(entity);
        return entityConverter.convert(entity);
    }

    private void validate(BlogDto dto) {
        if (dto.getName() == null) {
            throw new NotNullPropertyException(Blog.class, "name");
        }
        List<BlogMemberDto> members = dto.getMembers();
        if (members.stream().map(BlogMemberDto::getId).distinct().count() < members.size()) {
            throw new BlogMembersStateException("한 블로그에 같은 사람이 두 번 포함될 수 없습니다");
        }
        if (members.stream().anyMatch(member -> Objects.equals(member.getId(), 0))) {
            throw new BlogMembersStateException("유효하지 않은 아이디를 가진 유저가 존재합니다");
        }
    }

    private Blog initializeEntity(BlogDto dto) {
        if (dto.getImageUrl() == null) {
            dto.setImageUrl(BLOG_DEFAULT_IMAGE_URL);
        }
        StringBuilder keyBuilder = new StringBuilder();
        dto.getName().codePoints().forEach(keyBuilder::append);

        Blog entity = new Blog(dto.getName(), dto.getIntroduce(), dto.getImageUrl(), keyBuilder.toString());
        dto.getMembers().stream().map(memberDto -> {
            User user = new User(memberDto.getId(), memberDto.getName(), memberDto.getEmail(), memberDto.getNickName(), memberDto.getImageUrl());
            return new BlogMember(user, entity, memberDto.getRole());
        }).forEach(entity::addMember);
        return entity;
    }

    private void inviteMembers(Blog entity) {
        List<BlogMember> targetMembers = entity.getMembers()
                .stream()
                .filter(member -> Objects.equals(member.getRole(), MemberRole.INVITING))
                .collect(Collectors.toList());

        targetMembers.forEach(member -> {
            try {
                blogInvitationService.inviteMemberToBlog(member.getEmail(), entity.getName(), entity.getInvitationKey());
            } catch (MessagingException messagingException) {
                logger.error("이메일 전송에 실패하였습니다. 원인 : " + messagingException.getMessage());
                throw new BlogMembersStateException("초대 메일 전송에 실패하였습니다!");
            }
        });
    }
}
