package com.example.OvertimeTracker.service.factory;

import com.example.OvertimeTracker.dto.missingDate.MissingDayResponseDto;
import com.example.OvertimeTracker.dto.overTime.OverTimeResponseDto;
import com.example.OvertimeTracker.dto.user.UserResponseDto;
import com.example.OvertimeTracker.model.MissingWorkDays;
import com.example.OvertimeTracker.model.OverTimeWork;
import com.example.OvertimeTracker.model.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DtoFactory {


    public OverTimeResponseDto createMissingDayResponseDto(OverTimeWork overtime) {
        OverTimeResponseDto dto = new OverTimeResponseDto();
        dto.setOverTimeDateRegistration(overtime.getOverTimeDateRegistration());
        dto.setDescription(overtime.getDescription());
        dto.setOvertimeHours(overtime.getOvertimeHours());
        return dto;
    }

    public MissingDayResponseDto createMissingDayResponseDto(MissingWorkDays missingWorkDays) {
        MissingDayResponseDto dto = new MissingDayResponseDto();
        dto.setDate(missingWorkDays.getDate());
        dto.setReason(missingWorkDays.getReason());
        dto.setMissingHours(missingWorkDays.getMissingHours());
        return dto;
    }

    public UserResponseDto createUserResponseDto(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setEmail(user.getEmail());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        return dto;
    }
}
