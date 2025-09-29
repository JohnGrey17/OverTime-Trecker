package com.example.OvertimeTracker.service.overTime;

import com.example.OvertimeTracker.dto.overTime.OverTimeResponseDto;
import com.example.OvertimeTracker.dto.overTime.OvertimeCreateRequestDto;

import java.util.List;

public interface OvertimeTrackerService {

    void addNewOvertime(OvertimeCreateRequestDto requestDto, Long userId);

    List<OverTimeResponseDto> getAllByMonth(Long userId, int year, int month);

    List<OverTimeResponseDto> getAllByMonthAndUserId(Long userId, int year, int month);
}
