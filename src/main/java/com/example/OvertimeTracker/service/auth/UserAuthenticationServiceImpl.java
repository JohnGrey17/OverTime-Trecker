package com.example.OvertimeTracker.service.auth;

import com.example.OvertimeTracker.dto.user.userResponse.MessageResponseDto;
import com.example.OvertimeTracker.dto.user.registration.UserRegistrationRequestDto;
import com.example.OvertimeTracker.exceptions.types.DepartmentException;
import com.example.OvertimeTracker.exceptions.types.DomainException;
import com.example.OvertimeTracker.exceptions.types.UserException;
import com.example.OvertimeTracker.model.department.Department;
import com.example.OvertimeTracker.model.roles.Role;
import com.example.OvertimeTracker.model.roles.RoleName;
import com.example.OvertimeTracker.model.user.User;
import com.example.OvertimeTracker.repositories.DepartmentRepository;
import com.example.OvertimeTracker.repositories.RoleRepository;
import com.example.OvertimeTracker.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

    private final static String ACCEPTABLE_DOMAIN_NAME = "vyriy.com";
    private final static String SYMBOL_FOR_SUBSTRING = "@";


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final DepartmentRepository departmentRepository;

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
        user.setEmail(dto.getEmail().toLowerCase().trim());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setSalary(BigDecimal.ZERO); // 👈 Початкова зарплата 0 або як треба

        // 🎯 Перетворення String → Set<Department>

        user.setDepartment(getDepartment(dto.getSubDepartmentId()));
        return user;
    }

    private Department getDepartment(Long departmentId) {

        return departmentRepository.findById(
                departmentId).orElseThrow(() -> new DepartmentException("Department does not exist"));
    }


    // ✅ Валідація унікальності та правильності
    private void userRegistrationDetailsChecker(UserRegistrationRequestDto registrationRequestDto) {
        if (registrationRequestDto.getEmail() == null || registrationRequestDto.getEmail().isBlank()) {
            throw new UserException("Please insert your user email");
        }

        emailDomainNameChecker(registrationRequestDto.getEmail());

        if (registrationRequestDto.getPassword() == null || registrationRequestDto.getPassword().isBlank()
                || registrationRequestDto.getRepeatPassword() == null || registrationRequestDto.getRepeatPassword().isBlank()) {
            throw new UserException("Please insert password");
        }

        if (userRepository.findByEmail(registrationRequestDto.getEmail()).isPresent()) {
            throw new UserException("User with email " + registrationRequestDto.getEmail() + " already exists");
        }
    }

    /*
    Check Email domain address

     */
    private void emailDomainNameChecker(String email) {
        if (email == null || !email.contains(SYMBOL_FOR_SUBSTRING)) {
            throw new DomainException("Invalid email format");
        }

        String domain = email.substring(email.lastIndexOf(SYMBOL_FOR_SUBSTRING) + 1)
                .toLowerCase();

        if (!domain.equals(ACCEPTABLE_DOMAIN_NAME)) {
            throw new DomainException("Please use work email");
        }
    }
}
