package io.zerogone.service.create;

import io.zerogone.converter.Converter;
import io.zerogone.exception.BlogMembersStateException;
import io.zerogone.exception.NotNullPropertyException;
import io.zerogone.model.dto.BlogMemberDto;
import io.zerogone.model.dto.DataTransferObject;
import io.zerogone.model.entity.BlogMember;
import io.zerogone.repository.BlogMemberDao;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;

@Service
public class BlogMemberCreateTemplate extends CreateTemplate<BlogMember> {
    private final BlogMemberDao blogMemberDao;

    public BlogMemberCreateTemplate(Converter<BlogMember> converter, BlogMemberDao blogMemberDao) {
        super(converter);
        this.blogMemberDao = blogMemberDao;
    }

    @Override
    BlogMember saveEntity(BlogMember entity) {

        return entity;
    }

    @Override
    void validate(DataTransferObject dto) {
        BlogMemberDto memberDto = (BlogMemberDto) dto;
        if (memberDto.getId() != 0) {
            throw new IllegalArgumentException("생성 시 id를 부여하면 안됩니다");
        }
        if (memberDto.getUser() == null) {
            throw new NotNullPropertyException(BlogMember.class, "user");
        }
        if (memberDto.getBlog() == null) {
            throw new NotNullPropertyException(BlogMember.class, "blog");
        }
    }
}
