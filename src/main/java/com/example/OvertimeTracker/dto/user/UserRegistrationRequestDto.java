package com.example.OvertimeTracker.dto.user;

import com.example.OvertimeTracker.validator.FieldMatch;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.NumberFormat;

@FieldMatch(first = "password",second = "repeatPassword",
        message = "Password and repeat password should match")

@Data
public class UserRegistrationRequestDto {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Length(min = 4,max = 32)
    private String password;
    @NotBlank
    @Length(min = 4,max = 32)
    private String repeatPassword;

    private String firstName;
    private String lastName;

    private String phoneNumber;
    @NotBlank
    private String departmentName;
}
