package com.example.calculateRouteDemo.services;

public class NoWayException extends Exception {

    private static final long serialVersionUID = 1L;

    private final String message;

    public NoWayException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
