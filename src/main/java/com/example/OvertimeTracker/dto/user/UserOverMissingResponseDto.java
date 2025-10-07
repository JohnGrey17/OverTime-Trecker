package com.example.OvertimeTracker.dto.user;

import com.example.OvertimeTracker.dto.missingDate.MissingDayResponseDto;
import com.example.OvertimeTracker.dto.overTime.OverTimeResponseDto;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class UserOverMissingResponseDto {
    private Long userId;
    private String firstName;
    private String lastName;
    private BigDecimal baseSalary;
    private List<OverTimeResponseDto> overtimesDay;
    private List<MissingDayResponseDto> missingsDay;
    private BigDecimal totalMissingHours;
}