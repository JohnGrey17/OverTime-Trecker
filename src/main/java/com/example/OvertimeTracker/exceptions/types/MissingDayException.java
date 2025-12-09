package com.example.OvertimeTracker.exceptions.types;

public class MissingDayException extends RuntimeException {
    public MissingDayException(String message) {
        super(message);
    }
}
