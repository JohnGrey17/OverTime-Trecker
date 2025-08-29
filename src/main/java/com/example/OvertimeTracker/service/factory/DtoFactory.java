package com.example.OvertimeTracker.service.factory;

import com.example.OvertimeTracker.dto.overTime.OverTimeResponseDto;
import com.example.OvertimeTracker.model.OverTimeWork;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DtoFactory {


    public OverTimeResponseDto createDto(OverTimeWork overtime) {
        OverTimeResponseDto dto = new OverTimeResponseDto();
        dto.setOverTimeDateRegistrationTime(overtime.getOverTimeDateRegistration());
        dto.setDescription(overtime.getDescription());
        dto.setOvertimeHoursCount(overtime.getGetOvertimeHoursCount());
        return dto;
    }
}
