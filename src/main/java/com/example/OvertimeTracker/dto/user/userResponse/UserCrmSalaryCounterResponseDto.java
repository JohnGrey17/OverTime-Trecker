package com.example.OvertimeTracker.dto.user.userResponse;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserCrmSalaryCounterResponseDto {

    private BigDecimal hourRate;

    private BigDecimal overtimeX1;
    private BigDecimal overtimeX1_5;
    private BigDecimal overtimeX2;
    private BigDecimal totalSum;
    private BigDecimal totalDeductions;
    private BigDecimal netSalary;

    private BigDecimal bonusAmount;
}
