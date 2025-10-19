package com.example.OvertimeTracker.dto.expenses;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@ToString
@Getter
@Setter
public class ExpensesResponseDto {

    private LocalDate date;

    private String reason;

    private BigDecimal sum;

    private String filePath;
}
