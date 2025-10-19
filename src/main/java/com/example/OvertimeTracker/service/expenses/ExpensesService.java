package com.example.OvertimeTracker.service.expenses;

import com.example.OvertimeTracker.dto.expenses.ExpensesResponseDto;
import com.example.OvertimeTracker.dto.expenses.ExpensesRequestDto;

import java.util.List;

public interface ExpensesService {

    String addNewExpense(Long userId, ExpensesRequestDto requestDto);

    List<ExpensesResponseDto> getAllByUserIdAndMonth(Long userId, int year, int month);
}
