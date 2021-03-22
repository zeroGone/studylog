package io.zerogone.exception;

import javax.persistence.PersistenceException;

public class UniquePropertyException extends PersistenceException {
    public UniquePropertyException(String message) {
        super(message);
    }
}
