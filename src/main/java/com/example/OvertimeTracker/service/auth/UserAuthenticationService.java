package com.example.OvertimeTracker.service.auth;

import com.example.OvertimeTracker.dto.user.userResponse.MessageResponseDto;
import com.example.OvertimeTracker.dto.user.registration.UserRegistrationRequestDto;

public interface UserAuthenticationService {

    MessageResponseDto register(UserRegistrationRequestDto request);
}
