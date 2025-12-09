package com.example.OvertimeTracker.dto.user.userResponse;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserResponseDto {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private BigDecimal salary;

}
