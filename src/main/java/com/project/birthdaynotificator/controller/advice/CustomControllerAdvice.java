package com.project.birthdaynotificator.controller.advice;

import com.project.birthdaynotificator.exception.BindingExceptions;
import com.project.birthdaynotificator.exception.ModelNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class CustomControllerAdvice {
    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<String> handleException(ModelNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(BindingExceptions.class)
    public ResponseEntity<String> handleException(BindingExceptions e) {
        String errors = e.getBindingErrors().stream()
                .map(Object::toString)
                .reduce((v, acc) -> acc + v + '\n').orElse("");
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}
