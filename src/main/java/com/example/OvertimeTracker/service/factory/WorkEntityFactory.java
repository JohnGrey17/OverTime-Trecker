package com.example.OvertimeTracker.factory;

import com.example.OvertimeTracker.dto.missingDate.MissingWorkDateRequestDto;
import com.example.OvertimeTracker.dto.overTime.OvertimeCreateRequestDto;
import com.example.OvertimeTracker.exceptions.types.UserException;
import com.example.OvertimeTracker.model.MissingWorkDays;
import com.example.OvertimeTracker.model.OverTimeWork;
import com.example.OvertimeTracker.model.User;
import com.example.OvertimeTracker.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class WorkEntityFactory {

    private final UserRepository userRepository;

    public MissingWorkDays createMissingWorkDay(MissingWorkDateRequestDto dto, Long userId) {
        User user = getUser(userId);
        MissingWorkDays entity = new MissingWorkDays();
        entity.setDate(dto.getDate());
        entity.setReason(dto.getReason());
        entity.setMissingHours(dto.getMissingHours());
        entity.setUser(user);
        return entity;
    }

    public OverTimeWork createOvertimeWork(OvertimeCreateRequestDto dto, Long userId) {
        User user = getUser(userId);
        OverTimeWork entity = new OverTimeWork();
        entity.setOverTimeDateRegistration(dto.getOverTimeDateRegistration());
        entity.setDescription(dto.getDescription());
        entity.setOvertime_hours(dto.getOvertime_hours());
        entity.setMultiplier(calculateMultiplier(dto.getOverTimeDateRegistration()));
        entity.setUser(user);
        return entity;
    }

    private BigDecimal calculateMultiplier(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();
        return (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) ?
                BigDecimal.valueOf(1.5) : BigDecimal.ONE;
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserException("User with that id does not exist"));
    }
}
