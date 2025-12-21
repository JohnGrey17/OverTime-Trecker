package com.example.OvertimeTracker.service.salaryCounter.bonusCounter;

import com.example.OvertimeTracker.dto.bonus.BonusResponseDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
public class BonusCounterCounterServiceImpl implements BonusCounterService {

    @Override
    public BigDecimal getBonusesAmount(List<BonusResponseDto> bonuses) {
        return bonuses.stream()
                .map(BonusResponseDto::getSum)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
