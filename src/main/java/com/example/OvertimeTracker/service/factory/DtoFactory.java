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


    public OverTimeResponseDto createOverTimeResponseDto(OverTimeWork overtime) {
        OverTimeResponseDto dto = new OverTimeResponseDto();
        dto.setOverTimeDateRegistration(overtime.getOverTimeDateRegistration());
        dto.setDescription(overtime.getDescription());
        dto.setOvertimeHours(overtime.getOvertimeHours());
        return dto;
    }

    public MissingDayResponseDto createOverTimeResponseDto(MissingWorkDays missingWorkDays) {
        MissingDayResponseDto dto = new MissingDayResponseDto();
        dto.setDate(missingWorkDays.getDate());
        dto.setReason(missingWorkDays.getReason());
        dto.setMissingHours(missingWorkDays.getMissingHours());
        return dto;
    }
}
