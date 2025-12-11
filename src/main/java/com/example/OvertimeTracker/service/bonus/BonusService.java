package com.example.OvertimeTracker.service.bonus;

import com.example.OvertimeTracker.dto.bonus.BonusResponseDto;
import com.example.OvertimeTracker.dto.bonus.BonusRequestDto;
import com.example.OvertimeTracker.dto.bonus.BonusUpdateRequestDto;

import java.util.List;

public interface BonusService {

    String createBonus(Long userId, BonusRequestDto requestDto);

    List<BonusResponseDto> getAllByUserIdAndMonth(Long userId, int year, int month);

    void deleteBonus(Long bonusId, Long userId);

    void updateBonus(BonusUpdateRequestDto requestDto, Long userId, Long bonusId);

}
