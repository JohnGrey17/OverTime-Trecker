package com.example.OvertimeTracker.dto.salary;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SalaryCounterRequestDto {

    private LocalDate date;
}
