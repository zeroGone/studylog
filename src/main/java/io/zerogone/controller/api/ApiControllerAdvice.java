package io.zerogone.controller.api;

import ch.qos.logback.classic.Logger;
import io.zerogone.exception.*;
import io.zerogone.model.ErrorResponse;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class ApiControllerAdvice {
    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(NotExistedDataException.class)
    public ResponseEntity<ErrorResponse> handleNotExsitedDataException(NotExistedDataException notExistedDataException) {
        logger.info("Searching entity is failed!");
        return new ResponseEntity<>(
                new ErrorResponse.Builder()
                        .exception(NotExistedDataException.class)
                        .cause("검색 조건에 부합한 데이터가 존재하지 않음")
                        .statusCode(HttpStatus.NOT_FOUND)
                        .detail(notExistedDataException.getMessage())
                        .build()
                , HttpStatus.NOT_FOUND);
    }

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

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> handleBindException(BindException exception) {
        FieldError fieldError = exception.getFieldError();
        logger.error("유효성 검증 실패 : " + fieldError.toString());
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse
                        .Builder()
                        .exception(IllegalArgumentException.class)
                        .cause(fieldError.getDefaultMessage())
                        .detail("잘못된 값 : " + fieldError.getField())
                        .statusCode(HttpStatus.BAD_REQUEST)
                        .build());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException exception) {
        StringBuilder causes = new StringBuilder();
        StringBuilder invalidProperties = new StringBuilder();
        for (ConstraintViolation constraintViolation : exception.getConstraintViolations()) {
            causes.append(constraintViolation.getMessage()).append(" ");
            invalidProperties.append(constraintViolation.getPropertyPath().toString()).append(" ");
        }
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse
                        .Builder()
                        .exception(IllegalArgumentException.class)
                        .cause("[" + causes + "]")
                        .detail("잘못 입력된 속성 : [" + invalidProperties + "]")
                        .statusCode(HttpStatus.BAD_REQUEST)
                        .build());
    }

    @ExceptionHandler(NotExistDataException.class)
    public ResponseEntity<ErrorResponse> handleNotExistDataException(NotExistDataException exception) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse
                        .Builder()
                        .exception(NotExistDataException.class)
                        .cause(exception.getMessage())
                        .detail("입력된 값:[" + exception.getExceptionValue() + "]")
                        .statusCode(HttpStatus.BAD_REQUEST)
                        .build());
    }
}
