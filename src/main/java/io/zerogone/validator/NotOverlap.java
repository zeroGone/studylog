package io.zerogone.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = BlogMembersValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface NotOverlap {
    String message() default "블로그 멤버들은 중복되지 않아야 합니다";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
