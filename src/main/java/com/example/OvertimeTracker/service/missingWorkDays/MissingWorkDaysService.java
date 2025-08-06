package com.example.OvertimeTracker.service.missingWorkDays;

import com.example.OvertimeTracker.dto.MissingWorkDateRequestDto;

public interface MissingWorkDaysService {
    void addMissingWorkDay(MissingWorkDateRequestDto requestDto);
}
