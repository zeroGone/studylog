package io.zerogone.common.exception;

import java.util.function.Supplier;

public class NotExistDataException extends CustomRuntimeException implements Supplier<NotExistDataException> {
    public NotExistDataException(String message, Object exceptionValue) {
        super(message, exceptionValue);
    }

    @Override
    public NotExistDataException get() {
        return this;
    }
}
