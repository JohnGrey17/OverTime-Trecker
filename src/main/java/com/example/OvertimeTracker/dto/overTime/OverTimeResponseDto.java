package com.example.OvertimeTracker.dto.overTime;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class OverTimeResponseDto {
    private LocalDate overTimeDateRegistration;

    private String description;

    private BigDecimal overtime_hours;
}
