package com.example.OvertimeTracker.dto.missingDate;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class MissingDayResponseDto {

    private Long id;

    private LocalDate date;

    private String reason;

    private BigDecimal missingHours;
}
