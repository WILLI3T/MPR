package com.example.MPR;

public class CarAlreadyExistsException extends RuntimeException {
    public CarAlreadyExistsException() {
        super("Car already exists");
    }
}
