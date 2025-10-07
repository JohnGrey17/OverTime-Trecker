package com.example.OvertimeTracker.dto.salary;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserUpdateSalaryRequestDto {

    private BigDecimal salary;
}
