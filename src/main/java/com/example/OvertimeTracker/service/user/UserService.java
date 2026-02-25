package com.example.OvertimeTracker.service.user;

import com.example.OvertimeTracker.dto.salary.UserUpdateSalaryRequestDto;
import com.example.OvertimeTracker.dto.user.userResponse.UserResponseDto;
import com.example.OvertimeTracker.dto.user.update.user.UserPasswordUpdateRequestDto;
import com.example.OvertimeTracker.dto.user.update.user.UserUpdateRequestDto;

import java.util.List;

public interface UserService {

    List<UserResponseDto> getUsersByDepartment(Long departmentId);

    UserResponseDto getUserById(Long userId);

    String upgradeUserSalary(Long userId, UserUpdateSalaryRequestDto dto);


    void updateUserCardInfo(Long id, UserUpdateRequestDto requestDto);

    void changePassword(Long id, UserPasswordUpdateRequestDto requestDto);

    UserResponseDto getOwnInfo(Long id);

    void deleteUser(Long userId, Long userOwnId);

    void updateUserDepartment(Long userId, Long departmentId);

}
