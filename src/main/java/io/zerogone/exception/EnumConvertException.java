package io.zerogone.exception;

public class EnumConvertException extends IllegalArgumentException {
    private final String message;

    public EnumConvertException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
