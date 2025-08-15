package com.example.OvertimeTracker.service.salaryCounter;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface SalaryCounterService {

    BigDecimal calculateSalaryForUser(Long userId, LocalDate monthDate);
}
