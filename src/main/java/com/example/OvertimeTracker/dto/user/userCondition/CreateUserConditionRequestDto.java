package com.example.OvertimeTracker.dto.user.userCondition;

import com.example.OvertimeTracker.model.user.userCondition.UserConditionType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateUserConditionRequestDto {
    private UserConditionType type;     // required
      // nullable
    private BigDecimal amount;          // required

    private Integer priority;           // nullable -> default 0
    private Boolean active;             // nullable -> default true
}
