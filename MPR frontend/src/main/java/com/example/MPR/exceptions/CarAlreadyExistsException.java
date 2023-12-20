package com.example.MPR.exceptions;

public class CarAlreadyExistsException extends RuntimeException {
    public CarAlreadyExistsException() {
        super("Car already exists");
    }
}
