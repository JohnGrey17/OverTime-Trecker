package com.example.OvertimeTracker.service.salaryCounter.bonusCounter;

import com.example.OvertimeTracker.dto.bonus.BonusResponseDto;

import java.math.BigDecimal;
import java.util.List;

public interface BonusCounterService {

    BigDecimal getBonusesAmount(List<BonusResponseDto> bonusResponseDto);
}
