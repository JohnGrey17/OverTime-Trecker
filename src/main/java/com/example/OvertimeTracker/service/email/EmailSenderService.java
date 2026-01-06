package com.example.OvertimeTracker.service.email;



public interface EmailSenderService {

    void sentTempCode(String userEmail, String verificationCode);
}
