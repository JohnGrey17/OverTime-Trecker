package com.example.OvertimeTracker.service.crm;

import com.example.OvertimeTracker.dto.missingDate.MissingDayResponseDto;
import com.example.OvertimeTracker.dto.overTime.OverTimeResponseDto;
import com.example.OvertimeTracker.dto.user.UserOverMissingResponseDto;
import com.example.OvertimeTracker.dto.user.UserResponseDto;
import com.example.OvertimeTracker.service.factory.DtoFactory;
import com.example.OvertimeTracker.service.missingWorkDays.MissingWorkDaysService;
import com.example.OvertimeTracker.service.overTime.OvertimeTrackerService;
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


    @Override
    public List<UserOverMissingResponseDto> getAllUsersByDepartmentsId(Long departmentId, int year, int month) {
        List<UserResponseDto> usersByDepartment = userService.getUsersByDepartment(departmentId);

        return usersByDepartment.stream()
                .map(user -> {
                    List<OverTimeResponseDto> overtimes = overtimeTrackerService
                            .getAllByMonthAndUserId(user.getId(), year, month);
                    List<MissingDayResponseDto> missingDays = missingWorkDaysService
                            .getAllByMonthAndUserId(user.getId(), year, month);

                    BigDecimal totalMissing = missingDays.stream()
                            .map(MissingDayResponseDto::getMissingHours)
                            .reduce(BigDecimal.ZERO, BigDecimal::add);

                    UserOverMissingResponseDto dto = dtoFactory.createUserMissingResponseDto(user, overtimes, missingDays);
                    dto.setTotalMissingHours(totalMissing);

                    return dto;
                })
                .toList();
    }
}
