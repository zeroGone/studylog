package io.zerogone.exception;

public class NotExistedDataException extends CommonRuntimeException {
    public NotExistedDataException(Class<?> entityType, String value, String when) {
        super(entityType, value, when);
    }
}
