package com.example.OvertimeTracker.dto.overTime;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OverTimeUpdateRequestDto {

    private Long id;

    private String newDescription;

    private BigDecimal overtime_hours;
}
