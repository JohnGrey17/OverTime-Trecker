package com.example.OvertimeTracker.service.bonus;

import com.example.OvertimeTracker.dto.expenses.BonusResponseDto;
import com.example.OvertimeTracker.dto.expenses.BonusRequestDto;

import java.util.List;

public interface BonusService {

    String createBonus(Long userId, BonusRequestDto requestDto);

    List<BonusResponseDto> getAllByUserIdAndMonth(Long userId, int year, int month);
}
