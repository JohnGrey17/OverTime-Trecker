package com.example.OvertimeTracker.service.user.forgotPass;

public interface ForgotPasswordService {


    void sendResetCode(String email);

    void confirmReset(String email, String code, String newPassword, String confirmPassword);

}
