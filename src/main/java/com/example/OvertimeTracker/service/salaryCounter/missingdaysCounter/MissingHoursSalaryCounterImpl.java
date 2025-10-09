package com.example.OvertimeTracker.service.salaryCounter.missingdaysCounter;

import com.example.OvertimeTracker.dto.missingDate.MissingDayResponseDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class MissingHoursSalaryCounterImpl implements  MissingHoursSalaryCounter {

    @Override
    public BigDecimal getMissingSum(BigDecimal hourRate, List<MissingDayResponseDto> missingDays) {
        BigDecimal sumOfHours = BigDecimal.ZERO;

        for (MissingDayResponseDto dto : missingDays) {
            if (dto.getMissingHours() != null) {
                sumOfHours = sumOfHours.add(dto.getMissingHours());
            }
        }

        return hourRate.multiply(sumOfHours);
    }
}