package com.example.OvertimeTracker.dto.overTime;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class OverTimeResponseDto {
    private LocalDate overTimeDateRegistrationTime;

    private String description;

    private BigDecimal overtimeHoursCount;
}
