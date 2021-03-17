package io.zerogone.blog.exception;

public class MemberRoleConvertException extends IllegalArgumentException {
    private final String message;

    public MemberRoleConvertException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
