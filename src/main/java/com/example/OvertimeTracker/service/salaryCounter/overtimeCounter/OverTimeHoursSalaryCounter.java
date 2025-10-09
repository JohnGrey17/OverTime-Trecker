package com.example.OvertimeTracker.service.salaryCounter.overtimeCounter;

import com.example.OvertimeTracker.dto.overTime.OverTimeResponseDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface OverTimeHoursSalaryCounter {
    Map<String,BigDecimal> getOverTimeSum(BigDecimal hourRate, List<OverTimeResponseDto> overtimes);
}
