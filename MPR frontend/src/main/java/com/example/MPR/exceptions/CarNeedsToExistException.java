package com.example.MPR.exceptions;

import com.example.MPR.controllers.RestResponceEntituExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class CarNeedsToExistException extends RuntimeException {
    public CarNeedsToExistException(String message) {
        super(message);
    }
}
