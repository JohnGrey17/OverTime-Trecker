package com.example.OvertimeTracker.service.salaryCounter.missingdaysCounter;

import com.example.OvertimeTracker.dto.missingDate.MissingDayResponseDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface MissingHoursSalaryCounter {

    BigDecimal getMissingSum(BigDecimal hourRate, List<MissingDayResponseDto> missingHours);

}
