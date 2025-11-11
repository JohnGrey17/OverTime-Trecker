package com.example.OvertimeTracker.controller.user.admin;

import com.example.OvertimeTracker.dto.salary.UserUpdateSalaryRequestDto;
import com.example.OvertimeTracker.dto.user.UserResponseDto;
import com.example.OvertimeTracker.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/users")
@RequiredArgsConstructor
@Tag(name = "Admin: Users", description = "Administrator management of users.")
public class UserControllerAdmin {

    private final UserService userService;

    @Operation(summary = "Get users by department", description = "Lists all users belonging to a specific department.")
    @GetMapping("/department/{departmentId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponseDto> getUsersByDepartment(@PathVariable Long departmentId) {
        return userService.getUsersByDepartment(departmentId);
    }

    @Operation(summary = "Get user by ID", description = "Retrieves detailed information about a specific user.")
    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public UserResponseDto getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @Operation(summary = "Update user salary", description = "Updates salary parameters for a specific user.")
    @PutMapping("/{userId}/salary")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasRole('ADMIN')")
    public String updateUserSalary(
            @PathVariable Long userId,
            @RequestBody UserUpdateSalaryRequestDto dto) {
        return userService.upgradeUserSalary(userId, dto);
    }
}
