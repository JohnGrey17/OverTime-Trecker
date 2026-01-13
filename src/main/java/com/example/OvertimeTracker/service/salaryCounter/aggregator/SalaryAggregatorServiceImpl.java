package com.example.OvertimeTracker.service.salaryCounter.aggregator;

import com.example.OvertimeTracker.dto.bonus.BonusResponseDto;
import com.example.OvertimeTracker.dto.missingDate.MissingDayResponseDto;
import com.example.OvertimeTracker.dto.overTime.OverTimeResponseDto;
import com.example.OvertimeTracker.dto.user.userResponse.UserCrmSalaryCounterResponseDto;
import com.example.OvertimeTracker.model.user.userCondition.UserCondition;
import com.example.OvertimeTracker.repositories.UserConditionRepository;
import com.example.OvertimeTracker.service.factory.DtoFactory;
import com.example.OvertimeTracker.service.salaryCounter.bonusCounter.BonusCounterService;
import com.example.OvertimeTracker.service.salaryCounter.missingdaysCounter.MissingHoursSalaryCounter;
import com.example.OvertimeTracker.service.salaryCounter.overtimeCounter.OverTimeHoursSalaryCounter;
import com.example.OvertimeTracker.service.salaryCounter.perhourCounter.SalaryPerHourService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SalaryAggregatorServiceImpl implements SalaryAggregatorService {

    private final SalaryPerHourService salaryPerHourService;
    private final OverTimeHoursSalaryCounter overTimeHoursSalaryCounter;
    private final MissingHoursSalaryCounter missingHoursSalaryCounter;
    private final DtoFactory dtoFactory;
    private final BonusCounterService bonusCounterService;

    @Override
    public UserCrmSalaryCounterResponseDto getCrmResponseDto(Long userId, BigDecimal baseSalary,
                                                             List<OverTimeResponseDto> overTimeResponseDtoList,
                                                             List<MissingDayResponseDto> missingDayResponseDtoList,
                                                             List<BonusResponseDto> bonusResponseDtoList,int year, int months) {

        BigDecimal perHourValue = salaryPerHourService.getPerHourValue(baseSalary, year, months);
        Map<String, BigDecimal> overTimeSum = overTimeHoursSalaryCounter.getOverTimeSum(userId,perHourValue, overTimeResponseDtoList);
        BigDecimal missingSum = missingHoursSalaryCounter.getMissingSum(perHourValue, missingDayResponseDtoList);
        BigDecimal bonusAmount = bonusCounterService.getBonusesAmount(bonusResponseDtoList);

        return dtoFactory.createUserCrmResponseDto(overTimeSum, missingSum, baseSalary,bonusAmount);
    }
}
