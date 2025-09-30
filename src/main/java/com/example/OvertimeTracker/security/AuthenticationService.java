package com.example.OvertimeTracker.security;

import com.example.OvertimeTracker.dto.user.UserLoginRequestDto;
import com.example.OvertimeTracker.dto.user.UserLoginResponseDto;
import com.example.OvertimeTracker.model.roles.Role;
import com.example.OvertimeTracker.model.roles.RoleName;
import com.example.OvertimeTracker.model.user.User;
import com.example.OvertimeTracker.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JwtUtil jwtUtil;

    private final AuthenticationManager authenticationManager;

    private final RoleRepository roleRepository;

    public UserLoginResponseDto authenticate(UserLoginRequestDto requestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requestDto.email(), requestDto.password()));

        System.out.println("👤 Principal class: " + authentication.getPrincipal().getClass().getName());

        User user = (User) authentication.getPrincipal();
        Set<String> roles = user.getRoles().stream()
                .map(role -> role.getName().name())
                .collect(Collectors.toSet());

        String token = jwtUtil.generateToken(user.getUsername());

        return new UserLoginResponseDto(token, roles);
    }
}
