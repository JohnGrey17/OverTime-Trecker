package com.example.OvertimeTracker.service.user.userCondition;



import com.example.OvertimeTracker.dto.user.userCondition.CreateUserConditionRequestDto;
import com.example.OvertimeTracker.dto.user.userCondition.UpdateUserConditionRequestDto;
import com.example.OvertimeTracker.dto.user.userCondition.UserConditionResponseDto;

import java.util.List;

public interface UserConditionService {

    List<UserConditionResponseDto> getByUser(Long userId);

    UserConditionResponseDto create(Long userId, CreateUserConditionRequestDto request);

    UserConditionResponseDto update(Long userId, Long conditionId, UpdateUserConditionRequestDto request);

    void delete(Long userId, Long conditionId);
}
