package com.example.OvertimeTracker.service.crm;

import com.example.OvertimeTracker.dto.missingDate.MissingDayResponseDto;
import com.example.OvertimeTracker.dto.overTime.OverTimeResponseDto;
import com.example.OvertimeTracker.dto.user.UserCrmSalaryCounterResponseDto;
import com.example.OvertimeTracker.dto.user.UserCrmWithAllCount;
import com.example.OvertimeTracker.dto.user.UserResponseDto;
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


    @Override
    public List<UserCrmWithAllCount> getAllUsersByDepartmentsId(Long departmentId, int year, int month) {
        List<UserResponseDto> usersByDepartment = userService.getUsersByDepartment(departmentId);

        return usersByDepartment.stream()
                .map(user -> {
                    List<OverTimeResponseDto> overtimes = overtimeTrackerService
                            .getAllByMonthAndUserId(user.getId(), year, month);
                    List<MissingDayResponseDto> missingDays = missingWorkDaysService
                            .getAllByMonthAndUserId(user.getId(), year, month);

                    UserCrmSalaryCounterResponseDto salaryCounterResponseDto
                            = salaryAggregatorService.getCrmResponseDto(user.getSalary(), overtimes, missingDays);

                    BigDecimal totalMissingHours = missingDays.stream()
                            .map(MissingDayResponseDto::getMissingHours)
                            .reduce(BigDecimal.ZERO, BigDecimal::add);

                    UserCrmWithAllCount dto = dtoFactory.createUserMissingResponseDto(user, overtimes, missingDays, salaryCounterResponseDto );
                    dto.setTotalMissingHours(totalMissingHours);

                    return dto;
                })
                .toList();
    }
}
