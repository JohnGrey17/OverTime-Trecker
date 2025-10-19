package com.example.OvertimeTracker.service.salaryCounter.expensesCounter;

import com.example.OvertimeTracker.dto.expenses.ExpensesResponseDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
public class ExpensesCounterServiceImpl implements ExpensesCounterService {

    @Override
    public BigDecimal getExpensesAmount(List<ExpensesResponseDto> expenses) {
        return expenses.stream()
                .map(ExpensesResponseDto::getSum)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
