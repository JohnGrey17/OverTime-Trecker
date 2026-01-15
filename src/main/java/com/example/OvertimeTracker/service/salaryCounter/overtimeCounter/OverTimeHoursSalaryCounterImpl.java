package com.example.OvertimeTracker.service.salaryCounter.overtimeCounter;

import com.example.OvertimeTracker.dto.overTime.OverTimeResponseDto;
import com.example.OvertimeTracker.model.user.userCondition.UserCondition;
import com.example.OvertimeTracker.model.user.userCondition.UserConditionType;
import com.example.OvertimeTracker.repositories.UserConditionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OverTimeHoursSalaryCounterImpl implements OverTimeHoursSalaryCounter {

    private static final String KEY_HOURLY_RATE = "hourRate";
    private static final String TOTAL_OVERTIME_SUM = "totalOvertime";
    private static final String KEY_BREAKDOWN_X1 = "sumX1";
    private static final String KEY_BREAKDOWN_X1_5 = "sumX1_5";
    private static final String KEY_BREAKDOWN_X2 = "sumX2";

    private final UserConditionRepository userConditionRepository;

    @Override
    public Map<String, BigDecimal> getOverTimeSum(Long userId, BigDecimal hourRate, List<OverTimeResponseDto> overtimes) {

        BigDecimal sumX1 = BigDecimal.ZERO;
        BigDecimal sumX1_5 = BigDecimal.ZERO;
        BigDecimal sumX2 = BigDecimal.ZERO;

        // ✅ якщо є умова — беремо її amount і ігноруємо multiplier
        BigDecimal effectiveHourRate = hourRate;
        boolean ignoreMultiplier = false;

        if (userId != null) {
            UserCondition c = userConditionRepository
                    .findFirstByUserIdAndActiveTrueOrderByPriorityDescIdDesc(userId)
                    .orElse(null);

            if (c != null
                    && c.isActive()
                    && c.getType() == UserConditionType.FIXED_PER_OVERTIME
                    && c.getAmount() != null
                    && c.getAmount().signum() > 0) {

                effectiveHourRate = c.getAmount();
                ignoreMultiplier = true;
            }
        }

        for (OverTimeResponseDto o : overtimes) {
            BigDecimal multiplier = o.getMultiplier();
            BigDecimal hours = o.getOvertimeHours();

            if (hours == null) continue;

            // ✅ сума овертайму: або множимо на multiplier (як було), або ігноруємо
            BigDecimal overtimeSum;

            if (ignoreMultiplier) {
                // однакова ціна за 1 годину для будь-якого multiplier
                overtimeSum = effectiveHourRate.multiply(hours);
            } else {
                if (multiplier == null) continue;
                overtimeSum = effectiveHourRate.multiply(multiplier).multiply(hours);
            }

            // 🔹 розкладаємо по колонках (щоб UI показував x1/x1.5/x2)
            if (multiplier != null && multiplier.compareTo(BigDecimal.valueOf(1.0)) == 0) {
                sumX1 = sumX1.add(overtimeSum);
            } else if (multiplier != null && multiplier.compareTo(BigDecimal.valueOf(1.5)) == 0) {
                sumX1_5 = sumX1_5.add(overtimeSum);
            } else if (multiplier != null && multiplier.compareTo(BigDecimal.valueOf(2.0)) == 0) {
                sumX2 = sumX2.add(overtimeSum);
            } else {
                // якщо multiplier null/інший — щоб не загубити, кидаємо в x1
                sumX1 = sumX1.add(overtimeSum);
            }
        }

        BigDecimal totalSum = sumX1.add(sumX1_5).add(sumX2);

        return Map.of(
                KEY_HOURLY_RATE, effectiveHourRate,   // ✅ виводимо реальну ставку
                KEY_BREAKDOWN_X1, sumX1,
                KEY_BREAKDOWN_X1_5, sumX1_5,
                KEY_BREAKDOWN_X2, sumX2,
                TOTAL_OVERTIME_SUM, totalSum
        );
    }
}