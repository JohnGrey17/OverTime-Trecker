package com.example.OvertimeTracker.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class OvertimeRequestDto {

    private LocalDate overTimeDateRegistration;

    private String description;

    private BigDecimal overtime_hours;
}
