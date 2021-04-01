package io.zerogone.exception;

public class NotExistedDataException extends CommonRuntimeException {
    public NotExistedDataException(Class<?> entityType, String when, String value) {
        super(entityType, when, value);
    }
}
