package com.example.OvertimeTracker.service.salaryCounter.perhourCounter;

import java.math.BigDecimal;

public interface SalaryPerHourService {

    BigDecimal getPerHourValue(BigDecimal baseSalary, int year, int month);


}
