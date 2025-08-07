package com.example.OvertimeTracker.service.auth;

import com.example.OvertimeTracker.dto.MessageResponseDto;
import com.example.OvertimeTracker.dto.UserRegistrationRequestDto;

public interface UserAuthenticationService {

    MessageResponseDto register(UserRegistrationRequestDto request);
}
