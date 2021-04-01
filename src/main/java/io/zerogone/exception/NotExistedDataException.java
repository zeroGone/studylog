package io.zerogone.exception;

public class NotExistedDataException extends ExceptionTemplate {
    public NotExistedDataException(Class<?> entityType, String value, String when) {
        super(entityType, value, when);
    }
}
