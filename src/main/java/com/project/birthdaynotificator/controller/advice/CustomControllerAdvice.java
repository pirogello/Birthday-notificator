package com.project.birthdaynotificator.controller.advice;

import com.project.birthdaynotificator.dto.errors.ValidExceptionBody;
import com.project.birthdaynotificator.exception.ModelNotFoundException;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class CustomControllerAdvice {
    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<String> handleException(ModelNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleException(MethodArgumentNotValidException e) {
        var errors = e.getBindingResult().getFieldErrors()
                .stream()
//                .map(error -> new ValidExceptionBody(error.getObjectName(),
//                    error.getDefaultMessage(),
//                    error.getRejectedValue() == null ? "null" : error.getRejectedValue().toString(),
//                    error.getField())
//                )
                .map(FieldError::getDefaultMessage)
                .toList();
        return new ResponseEntity<>(errors.toString(), HttpStatus.BAD_REQUEST);
    }

    // JSON parse error
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleException(HttpMessageNotReadableException e) {
        var errors = e.getMessage();
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> handleException(AuthenticationException e) {
        var errors = e.getMessage();
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
