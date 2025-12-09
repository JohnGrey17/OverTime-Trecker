package com.example.OvertimeTracker.dto.user.update.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserPasswordUpdateRequestDto {

    private String currentPassword;
    @NotBlank
    @Length(min = 4,max = 32)
    private String newPassword;
    @NotBlank
    @Length(min = 4,max = 32)
    private String repeatPassword;
}
