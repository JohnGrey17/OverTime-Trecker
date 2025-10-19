package com.example.OvertimeTracker.dto.expenses;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class ExpensesRequestDto {

    private LocalDate date;

    private String reason;

    private BigDecimal sum;
}
