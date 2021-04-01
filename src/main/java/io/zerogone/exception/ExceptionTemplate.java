package io.zerogone.exception;

public class ExceptionTemplate extends RuntimeException {
    private final String message;

    public ExceptionTemplate(Class<?> entityType, String when, String value) {
        message = String.format("%s를 %s할 때 발생, 실제 값 : %s",
                entityType.getSimpleName(), when, value);
    }
    @Override
    public String getMessage() {
        return message;
    }
}
