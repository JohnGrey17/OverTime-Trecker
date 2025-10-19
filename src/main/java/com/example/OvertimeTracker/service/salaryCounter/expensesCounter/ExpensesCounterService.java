package com.example.OvertimeTracker.service.salaryCounter.expensesCounter;

import com.example.OvertimeTracker.dto.expenses.ExpensesResponseDto;

import java.math.BigDecimal;
import java.util.List;

public interface ExpensesCounterService {

    BigDecimal getExpensesAmount(List<ExpensesResponseDto> expensesResponseDto);
}
