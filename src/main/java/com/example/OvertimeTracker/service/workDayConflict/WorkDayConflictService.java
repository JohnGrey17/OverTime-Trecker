package com.example.OvertimeTracker.service.workDayConflict;

import com.example.OvertimeTracker.exceptions.types.WorkDayConflictException;
import com.example.OvertimeTracker.repositories.MissingWorkDaysRepository;
import com.example.OvertimeTracker.repositories.OvertimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class WorkDayConflictService {

    private final OvertimeRepository overTimeWorkRepository;
    private final MissingWorkDaysRepository missingWorkDaysRepository;

    public void validateOvertimeCreation(Long userId, LocalDate date) {
        if (missingWorkDaysRepository.existsByUserIdAndDate(userId, date)) {
            throw new WorkDayConflictException(
                    "Cannot add overtime for " + date + " because missing work day already exists"
            );
        }
    }

    public void validateMissingDayCreation(Long userId, LocalDate date) {
        if (overTimeWorkRepository.existsByUserIdAndOverTimeDateRegistration(userId, date)) {
            throw new WorkDayConflictException(
                    "Cannot add missing work day for " + date + " because overtime already exists"
            );
        }
    }
}
