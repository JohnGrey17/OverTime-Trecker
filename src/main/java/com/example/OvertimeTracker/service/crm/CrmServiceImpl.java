package com.example.OvertimeTracker.service.crm;

import com.example.OvertimeTracker.dto.bonus.BonusResponseDto;
import com.example.OvertimeTracker.dto.missingDate.MissingDayResponseDto;
import com.example.OvertimeTracker.dto.overTime.OverTimeResponseDto;
import com.example.OvertimeTracker.dto.user.userResponse.UserCrmSalaryCounterResponseDto;
import com.example.OvertimeTracker.dto.user.userResponse.UserCrmWithAllCount;
import com.example.OvertimeTracker.dto.user.userResponse.UserResponseDto;
import com.example.OvertimeTracker.service.bonus.BonusService;
import com.example.OvertimeTracker.service.factory.DtoFactory;
import com.example.OvertimeTracker.service.missingWorkDays.MissingWorkDaysService;
import com.example.OvertimeTracker.service.overTime.OvertimeTrackerService;
import com.example.OvertimeTracker.service.salaryCounter.aggregator.SalaryAggregatorService;
import com.example.OvertimeTracker.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CrmServiceImpl implements CrmService {

    private final OvertimeTrackerService overtimeTrackerService;
    private final MissingWorkDaysService missingWorkDaysService;
    private final DtoFactory dtoFactory;
    private final UserService userService;
    private final SalaryAggregatorService salaryAggregatorService;
    private final BonusService expensesService;


    @Override
    public List<UserCrmWithAllCount> getAllUsersByDepartmentsId(Long departmentId, int year, int month) {
        List<UserResponseDto> usersByDepartment = userService.getUsersByDepartment(departmentId);

        return usersByDepartment.stream()
                .map(user -> {
                    List<OverTimeResponseDto> overtimes = overtimeTrackerService
                            .getAllByMonthAndUserId(user.getId(), year, month);
                    List<MissingDayResponseDto> missingDays = missingWorkDaysService
                            .getAllByMonthAndUserId(user.getId(), year, month);

                    List<BonusResponseDto> expenses
                            = expensesService.getAllByUserIdAndMonth(user.getId(), year, month);

                    UserCrmSalaryCounterResponseDto salaryCounterResponseDto
                            = salaryAggregatorService.getCrmResponseDto(user.getId(), user.getSalary(),
                            overtimes,
                            missingDays,
                            expenses, year, month);

                    BigDecimal totalMissingHours = missingDays.stream()
                            .map(MissingDayResponseDto::getMissingHours)
                            .reduce(BigDecimal.ZERO, BigDecimal::add);

                    UserCrmWithAllCount dto = dtoFactory.createUserMissingResponseDto(user,
                            overtimes,
                            missingDays,
                            salaryCounterResponseDto);
                    dto.setTotalMissingHours(totalMissingHours);

                    return dto;
                })
                .toList();
    }
}
