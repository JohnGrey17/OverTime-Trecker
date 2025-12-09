package com.example.OvertimeTracker.dto.user.registration;

import java.util.Set;

public record UserLoginResponseDto(String token, Set<String> roles) {
}
