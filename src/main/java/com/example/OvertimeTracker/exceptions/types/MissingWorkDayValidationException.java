package com.example.OvertimeTracker.exceptions.types;

public class MissingWorkDayValidationException extends RuntimeException {
    public MissingWorkDayValidationException(String message) {
        super(message);
    }
}
