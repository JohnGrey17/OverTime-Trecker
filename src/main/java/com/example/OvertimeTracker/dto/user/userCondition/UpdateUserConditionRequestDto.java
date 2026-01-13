package com.example.OvertimeTracker.dto.user.userCondition;

import com.example.OvertimeTracker.model.user.userCondition.UserConditionType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateUserConditionRequestDto {
    private UserConditionType type;     // optional
         // optional (може бути null)
    private BigDecimal amount;          // optional

    private Integer priority;           // optional
    private Boolean active;             // optional
}
