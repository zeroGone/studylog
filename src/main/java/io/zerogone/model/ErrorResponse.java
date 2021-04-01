package io.zerogone.model;

import org.springframework.http.HttpStatus;

public class ErrorResponse {
    private Class<?> type;
    private String cause;
    private HttpStatus statusCode;
    private String detail;

    public String getType() {
        return type.getSimpleName();
    }

    public String getCause() {
        return cause;
    }

    public int getStatusCode() {
        return statusCode.value();
    }

    public String getDetail() {
        return detail;
    }

    private ErrorResponse(Builder builder) {
        type = builder.exception;
        cause = builder.cause;
        statusCode = builder.statusCode;
        detail = builder.detail;
    }

    public static class Builder {
        private Class<?> exception;
        private String cause;
        private HttpStatus statusCode;
        private String detail;

        public Builder exception(Class<?> exception) {
            this.exception = exception;
            return this;
        }

        public Builder cause(String cause) {
            this.cause = cause;
            return this;
        }

        public Builder statusCode(HttpStatus statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        public Builder detail(String detail) {
            this.detail = detail;
            return this;
        }

        public ErrorResponse build() {
            return new ErrorResponse(this);
        }
    }
}
