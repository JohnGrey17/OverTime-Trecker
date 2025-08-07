package com.example.OvertimeTracker.service.monthWorkDayChecher;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class MonthWorkDaysCheckerServiceImpl  implements MonthWorkDaysCheckerService {

    @Override
    public Integer countWorkDayInMonth() {

        LocalDate localDate = LocalDate.now();

        int year = localDate.getYear();
        int month = localDate.getMonthValue();
        int workingDays = 0;

        LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
        int lengthOfMonth = firstDayOfMonth.lengthOfMonth();

        for (int i = 0; i < lengthOfMonth; i++) {
            LocalDate current = firstDayOfMonth.plusDays(i);
            DayOfWeek day = current.getDayOfWeek();
            if (day != DayOfWeek.SATURDAY && day != DayOfWeek.SUNDAY) {
                workingDays++;
            }
        }
        return workingDays;
    }
}
