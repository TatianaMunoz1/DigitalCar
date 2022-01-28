package com.dh.digitalCar.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<String> runtimeException(MethodArgumentNotValidException e) {
        List<String> response = new ArrayList<>();
        for (ObjectError allError : e.getAllErrors()) {
            response.add(allError.getDefaultMessage());
        }
        return response;
    }
}
