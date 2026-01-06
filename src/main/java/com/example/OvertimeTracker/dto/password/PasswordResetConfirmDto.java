package com.example.OvertimeTracker.dto.password;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PasswordResetConfirmDto {
    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String code;

    @NotBlank
    private String newPassword;

    @NotBlank
    private String confirmPassword;
}
