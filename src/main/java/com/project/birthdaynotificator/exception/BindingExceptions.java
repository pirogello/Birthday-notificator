package com.project.birthdaynotificator.exception;

import lombok.Getter;
import org.springframework.validation.ObjectError;

import java.util.List;


@Getter
public class BindingExceptions extends Exception{
    List<ObjectError> bindingErrors;

    public BindingExceptions(List<ObjectError> errors) {
        bindingErrors = errors;
    }
}
