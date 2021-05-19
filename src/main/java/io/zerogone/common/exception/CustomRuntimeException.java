package io.zerogone.common.exception;

public class CustomRuntimeException extends RuntimeException {
    private final Object exceptionValue;

    public CustomRuntimeException(String message, Object exceptionValue) {
        super(message);
        this.exceptionValue = exceptionValue;
    }

    public Object getExceptionValue() {
        return exceptionValue;
    }
}
