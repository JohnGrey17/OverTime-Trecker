package com.example.OvertimeTracker.controller.user;


import com.example.OvertimeTracker.dto.user.MessageResponseDto;
import com.example.OvertimeTracker.dto.user.UserLoginRequestDto;
import com.example.OvertimeTracker.dto.user.UserLoginResponseDto;
import com.example.OvertimeTracker.dto.user.UserRegistrationRequestDto;
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
@Tag(name = "Authentication Controller",description = "This controller handles request "
        + "and response with registration and login")
public class AuthenticationController {

    private final UserAuthenticationService userService;
    private final AuthenticationService authenticationService;


    @Operation(summary = "Registration of a new user", description = "Registers a new user and assigns them a role.")
    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponseDto registerUser(@RequestBody @Valid UserRegistrationRequestDto requestDto) {

        return userService.register(requestDto);
    }

    @Operation(summary = "Login User",
            description = "Authenticates a user by their credentials and generates an access token "
                    + "for further interactions with protected endpoints. "
                    + "The provided credentials should include the user's username "
                    + "and password in the request body.")
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public UserLoginResponseDto login(@RequestBody UserLoginRequestDto requestDto) {

        return authenticationService.authenticate(requestDto);
    }
}
