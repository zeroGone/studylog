package io.zerogone.controller.advice;

import ch.qos.logback.classic.Logger;
import io.zerogone.exception.*;
import io.zerogone.model.ErrorResponse;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiControllerAdvice {
    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(FileUploadException.class)
    public ResponseEntity<ErrorResponse> handleFileUploadException(FileUploadException fileUploadException) {
        logger.error("Uploading file is failed. Something is wrong!");
        return new ResponseEntity<>(
                new ErrorResponse.Builder()
                        .exception(FileUploadException.class)
                        .cause("파일 업로드시 예상치 못한 에러발생")
                        .statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
                        .detail(fileUploadException.getMessage())
                        .build(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {BlogMembersStateException.class, UniquePropertyException.class, NotNullPropertyException.class})
    public ResponseEntity<ErrorResponse> handleBlogCreateException(Exception exception) {
        logger.debug("catch exception : " + exception.getMessage());
        return new ResponseEntity<>(
                new ErrorResponse.Builder()
                        .exception(exception.getClass())
                        .cause("데이터베이스 관련 조건에서 벗어남")
                        .statusCode(HttpStatus.BAD_REQUEST)
                        .detail(exception.getMessage())
                        .build(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(Exception exception) {
        logger.debug("catch exception : " + exception.getMessage());
        return new ResponseEntity<>(
                new ErrorResponse.Builder()
                        .exception(exception.getClass())
                        .cause(exception.getMessage())
                        .statusCode(HttpStatus.BAD_REQUEST)
                        .detail(exception.getMessage())
                        .build(),
                HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotExistDataException.class)
    public ErrorResponse handleNotExistDataException(NotExistDataException exception) {
        return new ErrorResponse
                .Builder()
                .exception(NotExistDataException.class)
                .cause(exception.getMessage())
                .detail("입력된 값:[" + exception.getExceptionValue() + "]")
                .statusCode(HttpStatus.NOT_FOUND)
                .build();
    }
}