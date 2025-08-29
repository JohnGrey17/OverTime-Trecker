package com.example.OvertimeTracker.security;

import com.example.OvertimeTracker.dto.user.UserLoginRequestDto;
import com.example.OvertimeTracker.dto.user.UserLoginResponseDto;
import com.example.OvertimeTracker.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JwtUtil jwtUtil;

    private final AuthenticationManager authenticationManager;

    public UserLoginResponseDto authenticate(UserLoginRequestDto requestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requestDto.email(), requestDto.password()));

        User user = (User) authentication.getPrincipal();

        String token = jwtUtil.generateToken(user.getUsername());

        return new UserLoginResponseDto(token);
    }
}
