package com.example.OvertimeTracker.service.salaryCounter;

import com.example.OvertimeTracker.model.MissingWorkDays;
import com.example.OvertimeTracker.model.OverTimeWork;
import com.example.OvertimeTracker.model.user.User;
import com.example.OvertimeTracker.repositories.MissingWorkDaysRepository;
import com.example.OvertimeTracker.repositories.OvertimeRepository;
import com.example.OvertimeTracker.repositories.UserRepository;
import com.example.OvertimeTracker.service.monthWorkDayChecher.MonthWorkDaysCheckerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;


@RequiredArgsConstructor
public class SalaryCounterServiceImpl implements SalaryCounterService {

//    private final OvertimeRepository overtimeRepository;
//    private final MissingWorkDaysRepository missingWorkDaysRepository;
//    private final MonthWorkDaysCheckerService monthWorkDaysCheckerService;
//    private final UserRepository userRepository;
//
//    @Override
//    public BigDecimal calculateSalaryForUser(Long userId, LocalDate monthDate) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        // 🔹 1. Загальна кількість робочих днів у місяці
//        int workingDays = monthWorkDaysCheckerService.countWorkDayInMonth();
//
//        // 🔹 2. Ставка за день
//        BigDecimal salaryPerDay = user.getSalary()
//                .divide(BigDecimal.valueOf(workingDays), 2, RoundingMode.HALF_UP);
//
//        // 🔹 3. Ставка за годину (8 год/день)
//        BigDecimal salaryPerHour = salaryPerDay
//                .divide(BigDecimal.valueOf(8), 2, RoundingMode.HALF_UP);
//
//        // 🔹 4. Межі місяця
//        int month = monthDate.getMonthValue();
//        int year = monthDate.getYear();
//
//        LocalDate start = LocalDate.of(year, month, 1);
//        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());
//
//        // 🔹 5. Пропущені години
////        List<MissingWorkDays> missedDays = missingWorkDaysRepository
////                .findAllByUser_IdAndDateBetween(userId, month, year);
//
////        BigDecimal totalMissedHours = missedDays.stream()
////                .map(MissingWorkDays::getMissingHours)
////                .reduce(BigDecimal.ZERO, BigDecimal::add);
//
//        // 🔹 6. Овертайми (тепер через Between)
//        List<OverTimeWork> overtimes = overtimeRepository
//                .findAllByUser_IdAndOverTimeDateRegistrationBetween(userId, start, end);
//
//        BigDecimal totalOvertimePayment = overtimes.stream()
//                .map(o -> o.getOvertimeHours().multiply(o.getMultiplier()))
//                .reduce(BigDecimal.ZERO, BigDecimal::add)
//                .multiply(salaryPerHour);
//
////        // 🔹 7. Вирахування за пропущені години
////        BigDecimal deduction = totalMissedHours.multiply(salaryPerHour);
//
////        // 🔹 8. Підсумкова зарплата
////        BigDecimal finalSalary = user.getSalary()
////                .subtract(deduction)
////                .add(totalOvertimePayment);
////
////        return finalSalary.setScale(2, RoundingMode.HALF_UP);

}

