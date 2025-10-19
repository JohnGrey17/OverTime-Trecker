package com.example.OvertimeTracker.service.salaryCounter.aggregator;

import com.example.OvertimeTracker.dto.expenses.ExpensesResponseDto;
import com.example.OvertimeTracker.dto.missingDate.MissingDayResponseDto;
import com.example.OvertimeTracker.dto.overTime.OverTimeResponseDto;
import com.example.OvertimeTracker.dto.user.UserCrmSalaryCounterResponseDto;
import com.example.OvertimeTracker.service.factory.DtoFactory;
import com.example.OvertimeTracker.service.salaryCounter.expensesCounter.ExpensesCounterService;
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
    private final ExpensesCounterService expensesCounterService;

    @Override
    public UserCrmSalaryCounterResponseDto getCrmResponseDto(BigDecimal baseSalary,
                                                             List<OverTimeResponseDto> overTimeResponseDtoList,
                                                             List<MissingDayResponseDto> missingDayResponseDtoList,
                                                             List<ExpensesResponseDto> expensesResponseDtoList) {

        BigDecimal perHourValue = salaryPerHourService.getPerHourValue(baseSalary);
        Map<String, BigDecimal> overTimeSum = overTimeHoursSalaryCounter.getOverTimeSum(perHourValue, overTimeResponseDtoList);
        BigDecimal missingSum = missingHoursSalaryCounter.getMissingSum(perHourValue, missingDayResponseDtoList);
        BigDecimal expensesAmount = expensesCounterService.getExpensesAmount(expensesResponseDtoList);

        return dtoFactory.createUserCrmResponseDto(overTimeSum, missingSum, baseSalary,expensesAmount);
    }

}
