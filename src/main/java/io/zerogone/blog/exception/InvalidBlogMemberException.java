package io.zerogone.blog.exception;

import javax.persistence.PersistenceException;

public class InvalidBlogMemberException extends PersistenceException {
    private static final String message = "존재하지 않은 유저가 포함되어 있습니다";

    @Override
    public String getMessage() {
        return message;
    }
}
