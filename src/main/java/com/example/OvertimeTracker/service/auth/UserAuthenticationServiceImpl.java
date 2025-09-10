package com.example.OvertimeTracker.service.auth;

import com.example.OvertimeTracker.dto.user.MessageResponseDto;
import com.example.OvertimeTracker.dto.user.UserRegistrationRequestDto;
import com.example.OvertimeTracker.exceptions.types.UserException;
import com.example.OvertimeTracker.model.roles.Role;
import com.example.OvertimeTracker.model.roles.RoleName;
import com.example.OvertimeTracker.model.user.Department;
import com.example.OvertimeTracker.model.user.User;
import com.example.OvertimeTracker.repositories.RoleRepository;
import com.example.OvertimeTracker.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public MessageResponseDto register(UserRegistrationRequestDto requestDto) {
        userRegistrationDetailsChecker(requestDto);

        User user = convertToUser(requestDto);

        Role role = roleRepository.findRoleByName(RoleName.USER)
                .orElseThrow(() -> new RuntimeException("❌ Role USER not found"));

        user.setRoles(Set.of(role));

        userRepository.save(user);

        return new MessageResponseDto(requestDto.getEmail() + " successfully registered");
    }

    // 🛠️ Приватний метод мапінгу з DTO в Entity
    private User convertToUser(UserRegistrationRequestDto dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setSalary(BigDecimal.ZERO); // 👈 Початкова зарплата 0 або як треба

        // 🎯 Перетворення String → Set<Department>
        Department department = mapDepartment(dto.getDepartmentName());
        user.setDepartmentName(department);
        return user;
    }

    private Department mapDepartment(String name) {
        if (name == null || name.isBlank()) {
            throw new UserException("❌ Department is required");
        }

        return Arrays.stream(Department.values())
                .filter(dept -> dept.getDisplayName().equalsIgnoreCase(name.trim()))
                .findFirst()
                .orElseThrow(() -> new UserException("❌ Unknown department: " + name));
    }

    // ✅ Валідація унікальності та правильності
    private void userRegistrationDetailsChecker(UserRegistrationRequestDto registrationRequestDto) {
        if (registrationRequestDto.getEmail() == null || registrationRequestDto.getEmail().isBlank()) {
            throw new UserException("Please insert your user email");
        }

        if (registrationRequestDto.getPassword() == null || registrationRequestDto.getPassword().isBlank()
                || registrationRequestDto.getRepeatPassword() == null || registrationRequestDto.getRepeatPassword().isBlank()) {
            throw new UserException("Please insert password");
        }

        if (userRepository.findByEmail(registrationRequestDto.getEmail()).isPresent()) {
            throw new UserException("User with email " + registrationRequestDto.getEmail() + " already exists");
        }
    }
}
