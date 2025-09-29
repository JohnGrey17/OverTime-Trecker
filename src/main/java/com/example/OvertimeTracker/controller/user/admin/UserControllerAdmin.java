package com.example.OvertimeTracker.controller.user.admin;

import com.example.OvertimeTracker.dto.user.UserResponseDto;
import com.example.OvertimeTracker.service.user.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Tag(name = "User Controller",description = "Thi controller give possibility to ADMIN do act with user ")
public class UserControllerAdmin {

    private final UserService userService;

    @GetMapping("/department/{departmentId}")
    @PreAuthorize("hasRole('ADMIN')")
    //todo виправити роль на адміна
    public List<UserResponseDto> getUsersByDepartment(@PathVariable Long departmentId) {
        return userService.getUsersByDepartment(departmentId);
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public UserResponseDto getUserById(@PathVariable Long userId){
        return userService.getUserById(userId);
    }
}
