package com.example.OvertimeTracker.service.missingWorkDays;

import com.example.OvertimeTracker.dto.missingDate.MissingWorkDateRequestDto;

public interface MissingWorkDaysService {
    void addMissingWorkDay(MissingWorkDateRequestDto requestDto, Long userId);
}
