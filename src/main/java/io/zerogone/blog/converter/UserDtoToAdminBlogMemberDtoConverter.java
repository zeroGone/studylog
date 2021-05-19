package io.zerogone.blog.converter;

import io.zerogone.blog.model.BlogMemberDto;
import io.zerogone.model.entity.MemberRole;
import org.springframework.stereotype.Component;

@Component
public class UserDtoToAdminBlogMemberDtoConverter extends UserDtoToBlogMemberDtoConverter {
    @Override
    void setRole(BlogMemberDto memberDto) {
        memberDto.setRole(MemberRole.ADMIN);
    }
}
