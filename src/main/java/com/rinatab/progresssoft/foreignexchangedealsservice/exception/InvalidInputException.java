package com.rinatab.progresssoft.foreignexchangedealsservice.exception;

public class InvalidInputException extends BaseException {
    public InvalidInputException(String message, int value, String path) {
        this.setMessage(message);
        this.setPath(path);
        this.setValue(value);
    }
}
