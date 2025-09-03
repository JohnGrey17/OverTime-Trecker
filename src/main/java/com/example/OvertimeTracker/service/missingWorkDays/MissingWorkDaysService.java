package com.example.OvertimeTracker.service.missingWorkDays;

import com.example.OvertimeTracker.dto.missingDate.MissingDayMonthRequestDto;
import com.example.OvertimeTracker.dto.missingDate.MissingDayResponseDto;
import com.example.OvertimeTracker.dto.missingDate.MissingWorkDateRequestDto;
import com.example.OvertimeTracker.dto.overTime.OverTimeMonthRequestDto;
import com.example.OvertimeTracker.dto.overTime.OverTimeResponseDto;

import java.util.List;

public interface MissingWorkDaysService {
    void addMissingWorkDay(MissingWorkDateRequestDto requestDto, Long userId);

    List<MissingDayResponseDto> getAllByMonth(Long id, MissingDayMonthRequestDto requestDto);
}
