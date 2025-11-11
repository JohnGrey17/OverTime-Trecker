package com.example.OvertimeTracker.controller.user.user;

import com.example.OvertimeTracker.dto.user.*;
import com.example.OvertimeTracker.security.AuthenticationService;
import com.example.OvertimeTracker.service.auth.UserAuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Handles registration and login processes for users.")
public class AuthenticationController {

    private final UserAuthenticationService userService;
    private final AuthenticationService authenticationService;

    @Operation(summary = "Register new user", description = "Creates a new user and assigns them a role in the system.")
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDto registerUser(@RequestBody @Valid UserRegistrationRequestDto requestDto) {
        return userService.register(requestDto);
    }

    @Operation(summary = "User login", description = "Authenticates the user and returns a JWT token for further requests.")
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public UserLoginResponseDto login(@RequestBody UserLoginRequestDto requestDto) {
        return authenticationService.authenticate(requestDto);
    }
}
