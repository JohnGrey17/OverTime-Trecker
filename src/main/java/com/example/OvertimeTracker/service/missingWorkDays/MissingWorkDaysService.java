package com.example.OvertimeTracker.service.missingWorkDays;

import com.example.OvertimeTracker.dto.missingDate.MissingDayResponseDto;
import com.example.OvertimeTracker.dto.missingDate.MissingWorkDateRequestDto;

import java.util.List;

public interface MissingWorkDaysService {
    void addMissingWorkDay(MissingWorkDateRequestDto requestDto, Long userId);

    List<MissingDayResponseDto> getAllByMonth(Long id, int year, int month);

    List<MissingDayResponseDto> getAllByMonthAndUserId(Long userId, int year, int month);
}
