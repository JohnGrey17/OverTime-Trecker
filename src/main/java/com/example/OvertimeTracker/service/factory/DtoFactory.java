package com.example.OvertimeTracker.service.factory;

import com.example.OvertimeTracker.dto.DepartmentResponseDto;
import com.example.OvertimeTracker.dto.expenses.ExpensesResponseDto;
import com.example.OvertimeTracker.dto.missingDate.MissingDayResponseDto;
import com.example.OvertimeTracker.dto.overTime.OverTimeResponseDto;
import com.example.OvertimeTracker.dto.user.UserCrmSalaryCounterResponseDto;
import com.example.OvertimeTracker.dto.user.UserCrmWithAllCount;
import com.example.OvertimeTracker.dto.user.UserResponseDto;
import com.example.OvertimeTracker.model.Expense;
import com.example.OvertimeTracker.model.MissingWorkDays;
import com.example.OvertimeTracker.model.OverTimeWork;
import com.example.OvertimeTracker.model.department.Department;
import com.example.OvertimeTracker.model.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class DtoFactory {


    private static final String KEY_HOURLY_RATE = "hourRate";
    private static final String TOTAL_OVERTIME_SUM = "totalOvertime";
    private static final String KEY_BREAKDOWN_X1 = "sumX1";
    private static final String KEY_BREAKDOWN_X1_5 = "sumX1_5";
    private static final String KEY_BREAKDOWN_X2 = "sumX2";

    public OverTimeResponseDto createMissingDayResponseDto(OverTimeWork overtime) {
        OverTimeResponseDto dto = new OverTimeResponseDto();
        dto.setId(overtime.getId());
        dto.setOverTimeDateRegistration(overtime.getOverTimeDateRegistration());
        dto.setDescription(overtime.getDescription());
        dto.setOvertimeHours(overtime.getOvertimeHours());
        dto.setMultiplier(overtime.getMultiplier());
        return dto;
    }

    public MissingDayResponseDto createMissingDayResponseDto(MissingWorkDays missingWorkDays) {
        MissingDayResponseDto dto = new MissingDayResponseDto();
        dto.setId(missingWorkDays.getId());
        dto.setDate(missingWorkDays.getDate());
        dto.setReason(missingWorkDays.getReason());
        dto.setMissingHours(missingWorkDays.getMissingHours());
        return dto;
    }

    public UserResponseDto createUserResponseDto(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setSalary(user.getSalary());
        return dto;
    }

    public DepartmentResponseDto createDepartmentResponseDto(Department department) {
        DepartmentResponseDto dto = new DepartmentResponseDto();
        dto.setId(department.getId());
        dto.setName(department.getName());
        dto.setCode(department.getCode());
        return dto;
    }

    public UserCrmWithAllCount createUserMissingResponseDto(
            UserResponseDto user,
            List<OverTimeResponseDto> overTimeDay,
            List<MissingDayResponseDto> missingTimeDay,
            UserCrmSalaryCounterResponseDto salaryCounterresponseDto) {
        UserCrmWithAllCount dto = new UserCrmWithAllCount();
        dto.setUserId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setBaseSalary(user.getSalary());
        dto.setOvertimesDay(overTimeDay);
        dto.setMissingsDay(missingTimeDay);

        dto.setOvertimeX1(round(salaryCounterresponseDto.getOvertimeX1()));
        dto.setOvertimeX1_5(round(salaryCounterresponseDto.getOvertimeX1_5()));
        dto.setOvertimeX2(round(salaryCounterresponseDto.getOvertimeX2()));
        dto.setTotalDeductions(round(salaryCounterresponseDto.getTotalDeductions()));
        dto.setTotalSum(salaryCounterresponseDto.getTotalSum());
        dto.setHourRate(round(salaryCounterresponseDto.getHourRate()));

        dto.setExpensesTotalSum(salaryCounterresponseDto.getExpensesAmount());
        return dto;
    }

    public UserCrmSalaryCounterResponseDto createUserCrmResponseDto(
            Map<String, BigDecimal> overTimeSum,
            BigDecimal missingSum,
            BigDecimal baseSalary,
            BigDecimal expensesAmount) {

        BigDecimal totalOvertime = overTimeSum.getOrDefault(TOTAL_OVERTIME_SUM, BigDecimal.ZERO);
        BigDecimal hourRate = overTimeSum.getOrDefault(KEY_HOURLY_RATE, BigDecimal.ZERO);
        BigDecimal x1 = overTimeSum.getOrDefault(KEY_BREAKDOWN_X1, BigDecimal.ZERO);
        BigDecimal x15 = overTimeSum.getOrDefault(KEY_BREAKDOWN_X1_5, BigDecimal.ZERO);
        BigDecimal x2 = overTimeSum.getOrDefault(KEY_BREAKDOWN_X2, BigDecimal.ZERO);

        UserCrmSalaryCounterResponseDto dto = new UserCrmSalaryCounterResponseDto();
        dto.setHourRate(hourRate);
        dto.setOvertimeX1(x1);
        dto.setOvertimeX1_5(x15);
        dto.setOvertimeX2(x2);
        dto.setTotalDeductions(missingSum);
        dto.setExpensesAmount(expensesAmount);

        BigDecimal totalSum = baseSalary.add(totalOvertime).subtract(missingSum).add(expensesAmount)
                .setScale(0, RoundingMode.HALF_UP);

        dto.setTotalSum(totalSum);

        return dto;
    }

    public ExpensesResponseDto createExpenseResponseFro(Expense expense) {
        ExpensesResponseDto dto = new ExpensesResponseDto();
        dto.setId(expense.getId());
        dto.setDate(expense.getDate());
        dto.setReason(expense.getReason());
        dto.setSum(expense.getSum());
        dto.setFilePath(expense.getFilePath());
        return dto;
    }

    private BigDecimal round(BigDecimal value) {
        return value == null ? BigDecimal.ZERO : value.setScale(2, RoundingMode.HALF_UP);
    }
}
