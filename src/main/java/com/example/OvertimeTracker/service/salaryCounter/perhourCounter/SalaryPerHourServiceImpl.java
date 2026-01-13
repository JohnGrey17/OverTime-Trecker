package com.example.OvertimeTracker.service.salaryCounter.perhourCounter;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.YearMonth;

@Service
public class SalaryPerHourServiceImpl implements SalaryPerHourService {

    private static final int DEFAULT_HOURS_PER_DAY = 8;

    @Override
    public BigDecimal getPerHourValue(BigDecimal baseSalary, int year, int month) {
        YearMonth ym = YearMonth.of(year, month);

        int workingDays = countWeekdays(ym);
        BigDecimal totalHours = BigDecimal.valueOf(workingDays)
                .multiply(BigDecimal.valueOf(DEFAULT_HOURS_PER_DAY));

        return baseSalary.divide(totalHours, 6, RoundingMode.HALF_UP);
    }

    private int countWeekdays(YearMonth month) {
        int count = 0;
        for (int day = 1; day <= month.lengthOfMonth(); day++) {
            DayOfWeek dow = month.atDay(day).getDayOfWeek();
            if (dow != DayOfWeek.SATURDAY && dow != DayOfWeek.SUNDAY) count++;
        }
        return count;
    }
}