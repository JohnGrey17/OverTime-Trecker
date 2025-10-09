package com.example.OvertimeTracker.service.salaryCounter.overtimeCounter;

import com.example.OvertimeTracker.dto.overTime.OverTimeResponseDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;

@Service
public class OverTimeHoursSalaryCounterImpl implements OverTimeHoursSalaryCounter {

    private static final String KEY_HOURLY_RATE = "hourRate";
    private static final String TOTAL_OVERTIME_SUM = "totalOvertime";
    private static final String KEY_BREAKDOWN_X1 = "sumX1";
    private static final String KEY_BREAKDOWN_X1_5 = "sumX1_5";
    private static final String KEY_BREAKDOWN_X2 = "sumX2";

    @Override
    public Map<String, BigDecimal> getOverTimeSum(BigDecimal hourRate, List<OverTimeResponseDto> overtimes) {

        BigDecimal sumX1 = BigDecimal.ZERO;
        BigDecimal sumX1_5 = BigDecimal.ZERO;
        BigDecimal sumX2 = BigDecimal.ZERO;

        // 🔹 1️⃣ Обходимо всі овертайми користувача
        for (OverTimeResponseDto o : overtimes) {
            BigDecimal multiplier = o.getMultiplier();
            BigDecimal hours = o.getOvertimeHours();

            if (multiplier == null || hours == null) continue;

            // Сума для конкретного множника
            BigDecimal overtimeRate = hourRate.multiply(multiplier);
            BigDecimal overtimeSum = overtimeRate.multiply(hours);

            // 🔹 2️⃣ Розкладаємо по типах
            if (multiplier.compareTo(BigDecimal.valueOf(1.0)) == 0) {
                sumX1 = sumX1.add(overtimeSum);
            } else if (multiplier.compareTo(BigDecimal.valueOf(1.5)) == 0) {
                sumX1_5 = sumX1_5.add(overtimeSum);
            } else if (multiplier.compareTo(BigDecimal.valueOf(2.0)) == 0) {
                sumX2 = sumX2.add(overtimeSum);
            }
        }

        // 🔹 3️⃣ Підсумок
        BigDecimal totalSum = sumX1.add(sumX1_5).add(sumX2);


        // 🔹 4️⃣ Повернення результатів
        return Map.of(
                KEY_HOURLY_RATE, hourRate,
                KEY_BREAKDOWN_X1, sumX1,
                KEY_BREAKDOWN_X1_5, sumX1_5,
                KEY_BREAKDOWN_X2, sumX2,
                TOTAL_OVERTIME_SUM, totalSum
        );
    }
}
