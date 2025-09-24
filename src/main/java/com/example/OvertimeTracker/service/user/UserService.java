package com.example.OvertimeTracker.service.user;

import com.example.OvertimeTracker.dto.user.UserResponseDto;

import java.util.List;

public interface UserService {

    List<UserResponseDto> findAllByDepartment(String departmentName);

    UserResponseDto findUserById(Long userId);
}
