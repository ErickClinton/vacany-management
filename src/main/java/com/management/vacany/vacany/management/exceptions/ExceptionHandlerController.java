package com.management.vacany.vacany.management.exceptions;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException)
    public void handleMethodArgumentNotValidException(MethodArgumentNotValidException e){

    }
}
