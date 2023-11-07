package com.example.MPR.exceptions;

public class CarNeedsToExistException extends RuntimeException {
    public CarNeedsToExistException() {
        super("Car needs to exist to be updated");
    }
}
