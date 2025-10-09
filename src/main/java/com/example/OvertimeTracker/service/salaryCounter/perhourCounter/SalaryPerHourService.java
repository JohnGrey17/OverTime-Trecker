package com.example.OvertimeTracker.service.salaryCounter.perhourCounter;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface SalaryPerHourService {

    BigDecimal getPerHourValue(BigDecimal baseSalary);

}
