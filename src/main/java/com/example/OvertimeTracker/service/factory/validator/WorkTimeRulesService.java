package com.example.OvertimeTracker.service.factory.validator;

import com.example.OvertimeTracker.exceptions.types.MissingWorkDayValidationException;
import com.example.OvertimeTracker.exceptions.types.OverTimeValidationException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;

@Service
public class WorkTimeRulesService {

    public BigDecimal calculateMultiplier(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();
        return (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY)
                ? BigDecimal.valueOf(1.5)
                : BigDecimal.ONE;
    }

    public void validateOvertimeHours(LocalDate date, BigDecimal overtimeHours) {
        if (date == null) {
            throw new OverTimeValidationException("Overtime date must not be null");
        }
        if (overtimeHours == null) {
            throw new OverTimeValidationException("Overtime hours must not be null");
        }
        if (overtimeHours.compareTo(BigDecimal.ZERO) <= 0) {
            throw new OverTimeValidationException("Overtime hours must be greater than 0");
        }

        DayOfWeek day = date.getDayOfWeek();
        BigDecimal maxHours;

        if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
            maxHours = BigDecimal.valueOf(8);
        } else {
            maxHours = BigDecimal.valueOf(5);
        }

        if (overtimeHours.compareTo(maxHours) > 0) {
            throw new OverTimeValidationException(
                    "Max overtime hours for " + day + " is " + maxHours + ", but got " + overtimeHours
            );
        }
    }

    public void validateMissingWorkDay(LocalDate date, BigDecimal missingHours) {
        if (date == null) {
            throw new MissingWorkDayValidationException("Date must not be null");
        }

        DayOfWeek day = date.getDayOfWeek();

        if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
            throw new MissingWorkDayValidationException(
                    "Missing work days cannot be registered on weekends (" + day + ")"
            );
        }

        if (missingHours == null) {
            throw new MissingWorkDayValidationException("Missing hours must not be null");
        }

        if (missingHours.compareTo(BigDecimal.ONE) < 0) {
            throw new MissingWorkDayValidationException(
                    "Missing hours must be at least 1 hour"
            );
        }

        if (missingHours.compareTo(BigDecimal.valueOf(8)) > 0) {
            throw new MissingWorkDayValidationException(
                    "Missing hours cannot be more than 8 hours per day"
            );
        }
    }
}
