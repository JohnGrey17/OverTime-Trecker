package com.example.OvertimeTracker.service.auth;

import com.example.OvertimeTracker.dto.user.MessageResponseDto;
import com.example.OvertimeTracker.dto.user.UserRegistrationRequestDto;
import com.example.OvertimeTracker.exceptions.types.UserException;
import com.example.OvertimeTracker.mapper.UserMapper;
import com.example.OvertimeTracker.model.user.User;
import com.example.OvertimeTracker.model.roles.Role;
import com.example.OvertimeTracker.model.roles.RoleName;
import com.example.OvertimeTracker.repositories.RoleRepository;
import com.example.OvertimeTracker.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;


    @Override
    @Transactional
    public MessageResponseDto register(UserRegistrationRequestDto requestDto) {
        userRegistrationDetailsChecker(requestDto);

        User user = userMapper.toModel(requestDto);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role role = roleRepository.findRoleByName(RoleName.USER)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        user.setRoles(Set.of(role));

        userRepository.save(user);

        return new MessageResponseDto(requestDto.getEmail() + " successfully registered");
    }

    private void userRegistrationDetailsChecker(UserRegistrationRequestDto registrationRequestDto) {

        if (registrationRequestDto.getEmail() == null || registrationRequestDto.getEmail().isBlank()) {
            throw new UserException("Please insert your user email");
        }

        if (registrationRequestDto.getPassword() == null
                || registrationRequestDto.getPassword().isBlank()
                || registrationRequestDto.getRepeatPassword() == null
                || registrationRequestDto.getRepeatPassword().isBlank()) {
            throw new UserException("Please insert password");
        }

        if (registrationRequestDto.getEmail() != null && !registrationRequestDto.getEmail().isBlank()) {
            if (userRepository.findByEmail(registrationRequestDto.getEmail()).isPresent()) {
                throw new UserException("User with email " + registrationRequestDto.getEmail()
                        + " already exists");
            }
        }
    }
}
