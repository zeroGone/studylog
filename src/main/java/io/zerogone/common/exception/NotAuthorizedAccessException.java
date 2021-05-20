package io.zerogone.common.exception;

import java.util.function.Supplier;

public class NotAuthorizedAccessException extends CustomRuntimeException implements Supplier<NotAuthorizedAccessException> {
    public NotAuthorizedAccessException(String detail) {
        super("허가 되지 않은 접근입니다", detail);
    }

    @Override
    public NotAuthorizedAccessException get() {
        return this;
    }
}
