package com.example.OvertimeTracker.dto.user.userResponse;

import com.example.OvertimeTracker.dto.missingDate.MissingDayResponseDto;
import com.example.OvertimeTracker.dto.overTime.OverTimeResponseDto;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class UserCrmWithAllCount {
    private Long userId;
    private String firstName;
    private String lastName;
    private BigDecimal baseSalary;
    private List<OverTimeResponseDto> overtimesDay;
    private List<MissingDayResponseDto> missingsDay;
    private BigDecimal totalMissingHours;

    private BigDecimal hourRate;

    private BigDecimal overtimeX1;
    private BigDecimal overtimeX1_5;
    private BigDecimal overtimeX2;
    private BigDecimal totalSum;
    private BigDecimal totalDeductions;

    private BigDecimal bonusTotalSum;

}