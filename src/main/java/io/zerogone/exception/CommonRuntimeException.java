package io.zerogone.exception;

public class CommonRuntimeException extends RuntimeException {
    private final String message;

    public CommonRuntimeException(Class<?> entityType, String when, String value) {
        message = String.format("%s를 %s할 때 발생, 실제 값 : %s",
                entityType.getSimpleName(), when, value);
    }
    @Override
    public String getMessage() {
        return message;
    }
}
