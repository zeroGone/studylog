package io.zerogone.controller;

import ch.qos.logback.classic.Logger;
import io.zerogone.exception.BlogMembersStateException;
import io.zerogone.exception.FileUploadException;
import io.zerogone.exception.NotNullPropertyException;
import io.zerogone.exception.UniquePropertyException;
import io.zerogone.model.ErrorResponse;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.NoResultException;

@RestControllerAdvice
public class ApiControllerAdvice {
    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<ErrorResponse> handleNoResultException() {
        logger.debug("Searching entity is failed!");
        return new ResponseEntity<>(new ErrorResponse("검색 결과 없음"), HttpStatus.OK);
    }

    @ExceptionHandler(value = {BlogMembersStateException.class, UniquePropertyException.class, NotNullPropertyException.class})
    public ResponseEntity<ErrorResponse> handleBlogCreateException(Exception exception) {
        logger.debug("catch exception : " + exception.getMessage());
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileUploadException.class)
    public ResponseEntity<ErrorResponse> handleFileUploadException(FileUploadException fileUploadException) {
        return new ResponseEntity<>(new ErrorResponse(fileUploadException.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
