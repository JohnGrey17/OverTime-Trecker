package com.example.OvertimeTracker.service.bonus;

import com.example.OvertimeTracker.dto.expenses.ExpensesResponseDto;
import com.example.OvertimeTracker.dto.expenses.ExpensesRequestDto;

import java.util.List;

public interface BonusService {

    String createBonus(Long userId, ExpensesRequestDto requestDto);

    List<ExpensesResponseDto> getAllByUserIdAndMonth(Long userId, int year, int month);
}
