package com.example.OvertimeTracker.service.salaryCounter.perhourCounter;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class SalaryPerHourServiceImpl implements SalaryPerHourService {

    private static final BigDecimal AVERAGE_HOURS_PER_MONTH = BigDecimal.valueOf(173.33);


    @Override
    public BigDecimal getPerHourValue(BigDecimal baseSalary) {
        return baseSalary.divide(AVERAGE_HOURS_PER_MONTH, 6, RoundingMode.HALF_UP);
    }

}
