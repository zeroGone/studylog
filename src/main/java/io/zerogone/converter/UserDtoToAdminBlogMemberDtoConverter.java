package io.zerogone.converter;

import io.zerogone.model.dto.BlogMemberDto;
import io.zerogone.model.entity.MemberRole;
import org.springframework.stereotype.Component;

@Component
public class UserDtoToAdminBlogMemberDtoConverter extends UserDtoToBlogMemberDtoConverter {
    @Override
    void setRole(BlogMemberDto memberDto) {
        memberDto.setRole(MemberRole.ADMIN);
    }
}
