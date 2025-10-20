package com.example.OvertimeTracker.dto.overTime;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class OverTimeUpdateRequestDto {

    private LocalDate newDateOfOverTime;

    private String newDescription;

    private BigDecimal overtime_hours;
}
