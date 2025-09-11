package com.example.OvertimeTracker.dto.missingDate;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class MissingWorkDateRequestDto {

    private String reason;

    private LocalDate date;

    private BigDecimal missingHours;
}
