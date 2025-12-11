package com.example.OvertimeTracker.dto.bonus;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BonusUpdateRequestDto {

    private BigDecimal bonusSum;

    private String reason;
}
