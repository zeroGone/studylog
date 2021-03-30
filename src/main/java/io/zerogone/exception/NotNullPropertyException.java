package io.zerogone.exception;

import javax.persistence.PersistenceException;

public class NotNullPropertyException extends PersistenceException {
    public NotNullPropertyException(Class<?> classType, String propertyName) {
        super(String.format("%s의 %s는 Null일 수 없습니다", classType.getName(), propertyName));
    }
}
