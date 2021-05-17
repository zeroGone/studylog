package io.zerogone.exception;

import java.util.function.Supplier;

public class NotAuthorizedException extends CustomRuntimeException implements Supplier<NotAuthorizedException> {
    public NotAuthorizedException(String detail) {
        super("허가 되지 않은 접근입니다", detail);
    }

    @Override
    public NotAuthorizedException get() {
        return this;
    }
}
