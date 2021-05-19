package io.zerogone.common.exception;

public class NotExistDataException extends CustomRuntimeException {
    public NotExistDataException(String message, Object exceptionValue) {
        super(message, exceptionValue);
    }
}
