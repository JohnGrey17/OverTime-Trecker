package com.example.OvertimeTracker.exceptions.types;

import jakarta.mail.MessagingException;

public class EmailSenderException extends RuntimeException {
    public EmailSenderException(String message, Throwable cause) {
        super(message, cause);
    }
}
