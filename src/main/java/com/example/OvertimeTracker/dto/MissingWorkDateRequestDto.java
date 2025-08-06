package com.example.OvertimeTracker.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class MissingWorkDateRequestDto {

    private String Reason;

    private LocalDate date;

    private BigDecimal missingHours;
}
