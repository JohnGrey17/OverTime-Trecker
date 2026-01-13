package com.example.OvertimeTracker.dto.user.userCondition;

import com.example.OvertimeTracker.model.user.userCondition.UserConditionType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserConditionResponseDto {
    private Long id;
    private Long userId;

    private UserConditionType type;

    private BigDecimal amount;

    private int priority;
    private boolean active;
}
