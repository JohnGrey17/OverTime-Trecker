package com.example.OvertimeTracker.service.factory;

import com.example.OvertimeTracker.dto.missingDate.MissingDayResponseDto;
import com.example.OvertimeTracker.dto.overTime.OverTimeResponseDto;
import com.example.OvertimeTracker.model.MissingWorkDays;
import com.example.OvertimeTracker.model.OverTimeWork;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DtoFactory {


    public OverTimeResponseDto createMissingDayresponseDto(OverTimeWork overtime) {
        OverTimeResponseDto dto = new OverTimeResponseDto();
        dto.setOverTimeDateRegistrationTime(overtime.getOverTimeDateRegistration());
        dto.setDescription(overtime.getDescription());
        dto.setOvertimeHoursCount(overtime.getOvertime_hours());
        return dto;
    }

    public MissingDayResponseDto createMissingDayresponseDto(MissingWorkDays missingWorkDays) {
        MissingDayResponseDto dto = new MissingDayResponseDto();
        dto.setDate(missingWorkDays.getDate());
        dto.setReason(missingWorkDays.getReason());
        dto.setMissingHours(missingWorkDays.getMissingHours());
        return dto;
    }
}
