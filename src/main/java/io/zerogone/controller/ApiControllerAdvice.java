package io.zerogone.controller;

import ch.qos.logback.classic.Logger;
import io.zerogone.blogmember.exception.BlogMembersStateException;
import io.zerogone.exception.UniquePropertyException;
import io.zerogone.model.ErrorResponse;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiControllerAdvice {
    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(value = {BlogMembersStateException.class, UniquePropertyException.class})
    public ResponseEntity<ErrorResponse> handleBlogCreateException(Exception exception) {
        logger.debug("catch exception : " + exception.getMessage());
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
