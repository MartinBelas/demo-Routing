package com.example.calculateRouteDemo.services;

public class NoWayException extends Exception {

    private static final long serialVersionUID = 1L;

    private String message;

    public NoWayException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
