package com.example.OvertimeTracker.service.auth;

import com.example.OvertimeTracker.dto.user.MessageResponseDto;
import com.example.OvertimeTracker.dto.user.UserRegistrationRequestDto;

public interface UserAuthenticationService {

    MessageResponseDto register(UserRegistrationRequestDto request);
}
