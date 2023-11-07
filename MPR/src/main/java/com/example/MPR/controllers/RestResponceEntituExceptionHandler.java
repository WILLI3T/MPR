package com.example.MPR.controllers;

import com.example.MPR.exceptions.CarAlreadyExistsException;
import com.example.MPR.exceptions.CarNeedsToExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class RestResponceEntituExceptionHandler extends ResponseEntityExceptionHandler{
    @ExceptionHandler(value = {IllegalAccessException.class})
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(CarAlreadyExistsException.class)
    protected ResponseEntity<Object> handleBadRequest(RuntimeException ex, WebRequest request){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }
    @ExceptionHandler(CarNeedsToExistException.class)
    protected ResponseEntity<Object> handleBadRequest2(RuntimeException ex, WebRequest request){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }
}

