package io.zerogone.controller.advice;

import io.zerogone.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidationControllerAdvice {
    @ExceptionHandler
    public ErrorResponse MissingServletRequestParameterException(MissingServletRequestParameterException exception) {
        return new ErrorResponse
                .Builder()
                .exception(MissingServletRequestParameterException.class)
                .cause(exception.getParameterName() + "이 없습니다")
                .detail("해당 요청 파라미터에 " + exception.getParameterName() + "를 포함하십시오")
                .statusCode(HttpStatus.BAD_REQUEST)
                .build();
    }

    @ExceptionHandler(BindException.class)
    public ErrorResponse handleBindException(BindException exception) {
        FieldError fieldError = exception.getFieldError();
        return new ErrorResponse
                .Builder()
                .exception(IllegalArgumentException.class)
                .cause(fieldError.getDefaultMessage())
                .detail("잘못된 값 : " + fieldError.getField())
                .statusCode(HttpStatus.BAD_REQUEST)
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();
        FieldError fieldError = bindingResult.getFieldError();
        return new ErrorResponse
                .Builder()
                .exception(exception.getClass())
                .cause(fieldError.getDefaultMessage())
                .detail("잘못된 값 : " + fieldError.getField())
                .statusCode(HttpStatus.BAD_REQUEST)
                .build();
    }

    @ExceptionHandler(javax.validation.ConstraintViolationException.class)
    public ErrorResponse handleConstraintViolationException(javax.validation.ConstraintViolationException exception) {
        List<String> causes = new ArrayList<>();
        List<String> params = new ArrayList<>();
        for (ConstraintViolation<?> constraintViolation : exception.getConstraintViolations()) {
            causes.add(constraintViolation.getMessage());
            String propertyFullName = constraintViolation.getPropertyPath().toString();
            params.add(propertyFullName.substring(propertyFullName.lastIndexOf(".") + 1));
        }

        return new ErrorResponse
                .Builder()
                .exception(IllegalArgumentException.class)
                .cause(causes.toString())
                .detail("잘못된 속성:" + params)
                .statusCode(HttpStatus.BAD_REQUEST)
                .build();
    }

    @ExceptionHandler(org.hibernate.exception.ConstraintViolationException.class)
    public ErrorResponse handleConstraintViolationException(org.hibernate.exception.ConstraintViolationException exception) {
        return new ErrorResponse
                .Builder()
                .exception(IllegalArgumentException.class)
                .cause(exception.getSQLException().getMessage())
                .detail("잘못된 속성:" + exception.getConstraintName())
                .statusCode(HttpStatus.BAD_REQUEST)
                .build();
    }
}
