package com.rinatab.progresssoft.foreignexchangedealsservice.exception;

public class InvalidCurrencyIsoCodeException extends BaseException{
    public InvalidCurrencyIsoCodeException(String message, int value, String path) {
        this.setMessage(message);
        this.setPath(path);
        this.setValue(value);
    }
}
