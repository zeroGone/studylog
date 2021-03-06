package io.zerogone.blog.validator;

import io.zerogone.blog.model.BlogMemberDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class BlogMembersValidator implements ConstraintValidator<NotOverlap, List<BlogMemberDto>> {
    @Override
    public boolean isValid(List<BlogMemberDto> members, ConstraintValidatorContext context) {
        return members == null || members.size() == 0 || members.stream().distinct().count() == members.size();
    }
}
