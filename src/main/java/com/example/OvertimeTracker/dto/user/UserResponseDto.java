package com.example.OvertimeTracker.dto.user;

import lombok.Data;

import java.util.List;

@Data
public class UserResponseDto {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private List<String> userRoles;
}
