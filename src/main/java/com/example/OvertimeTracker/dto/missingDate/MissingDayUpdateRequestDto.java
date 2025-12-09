package com.example.OvertimeTracker.dto.missingDate;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MissingDayUpdateRequestDto {

    private Long id;

    private String reason;

    private BigDecimal missingHours;

}
