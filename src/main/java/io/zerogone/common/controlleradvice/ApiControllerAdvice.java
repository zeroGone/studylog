package io.zerogone.common.controlleradvice;

import ch.qos.logback.classic.Logger;
import io.zerogone.common.ErrorResponse;
import io.zerogone.common.exception.CustomRuntimeException;
import io.zerogone.common.exception.NotAuthorizedAccessException;
import io.zerogone.common.exception.NotExistDataException;
import io.zerogone.common.fileupload.FileUploadException;
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

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(CustomRuntimeException.class)
    public ErrorResponse handleBlogCreateException(CustomRuntimeException exception) {
        logger.debug("catch exception : " + exception.getMessage());
        return new ErrorResponse.Builder()
                .exception(exception.getClass())
                .cause(exception.getMessage())
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
                .detail(exception.getExceptionValue().toString())
                .build();
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(NotAuthorizedAccessException.class)
    public ErrorResponse handleNotAuthorizedException(NotAuthorizedAccessException exception) {
        return new ErrorResponse.Builder()
                .exception(exception.getClass())
                .cause(exception.getMessage())
                .statusCode(HttpStatus.UNAUTHORIZED)
                .detail(exception.getExceptionValue().toString())
                .build();
    }
}
