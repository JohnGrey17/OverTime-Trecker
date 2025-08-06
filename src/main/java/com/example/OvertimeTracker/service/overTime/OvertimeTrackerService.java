package com.example.OvertimeTracker.service.overTime;

import com.example.OvertimeTracker.dto.OvertimeRequestDto;

public interface OvertimeTrackerService {

    void addNewOvertime(OvertimeRequestDto requestDto);
}
