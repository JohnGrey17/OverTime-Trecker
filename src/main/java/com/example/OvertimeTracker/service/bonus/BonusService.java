package com.example.OvertimeTracker.service.bonus;

import com.example.OvertimeTracker.dto.bonus.BonusResponseDto;
import com.example.OvertimeTracker.dto.bonus.BonusRequestDto;

import java.util.List;

public interface BonusService {

    String createBonus(Long userId, BonusRequestDto requestDto);

    List<BonusResponseDto> getAllByUserIdAndMonth(Long userId, int year, int month);

    void deleteBonus(Long userId, Long bonusId);
    void updateBonus(Long userId, Long bonusId, BonusRequestDto dto);
}
