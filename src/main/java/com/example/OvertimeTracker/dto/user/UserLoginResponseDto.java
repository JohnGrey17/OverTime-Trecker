package com.example.OvertimeTracker.dto.user;

import java.util.Set;

public record UserLoginResponseDto(String token, Set<String> roles) {
}
