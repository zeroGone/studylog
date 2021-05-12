package io.zerogone.exception;

public class UniquePropertyException extends CustomRuntimeException {
    public UniquePropertyException(String message, Object exceptionValue) {
        super(message, exceptionValue);
    }
}
