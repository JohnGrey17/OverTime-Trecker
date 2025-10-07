package com.example.OvertimeTracker.service.user;

import com.example.OvertimeTracker.dto.salary.UserUpdateSalaryRequestDto;
import com.example.OvertimeTracker.dto.user.UserResponseDto;

import java.util.List;

public interface UserService {

    List<UserResponseDto> getUsersByDepartment(Long departmentId);

    UserResponseDto getUserById(Long userId);

    String upgradeUserSalary(Long userId, UserUpdateSalaryRequestDto dto);


}
