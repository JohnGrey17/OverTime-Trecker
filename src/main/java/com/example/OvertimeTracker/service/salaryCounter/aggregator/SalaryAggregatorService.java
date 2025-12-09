package com.example.OvertimeTracker.service.salaryCounter.aggregator;

import com.example.OvertimeTracker.dto.expenses.ExpensesResponseDto;
import com.example.OvertimeTracker.dto.missingDate.MissingDayResponseDto;
import com.example.OvertimeTracker.dto.overTime.OverTimeResponseDto;
import com.example.OvertimeTracker.dto.user.userResponse.UserCrmSalaryCounterResponseDto;

import java.math.BigDecimal;
import java.util.List;

public interface SalaryAggregatorService {

    UserCrmSalaryCounterResponseDto getCrmResponseDto(BigDecimal baseSalary,
                                                      List<OverTimeResponseDto> overTimeResponseDtoList,
                                                      List<MissingDayResponseDto> missingDayResponseDtoList,
                                                      List<ExpensesResponseDto> expensesResponseDtoList);

}
