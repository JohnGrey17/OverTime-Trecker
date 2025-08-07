package com.example.OvertimeTracker.service.salaryCounter;

import com.example.OvertimeTracker.model.MissingWorkDays;
import com.example.OvertimeTracker.model.OverTimeWork;
import com.example.OvertimeTracker.model.User;
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

@Service
@RequiredArgsConstructor
public class SalaryCounterServiceImpl {
//
//    private final OvertimeRepository overtimeRepository;
//    private final MissingWorkDaysRepository missingWorkDaysRepository;
//    private final MonthWorkDaysCheckerService monthWorkDaysCheckerService;
//    private final UserRepository userRepository;
//
//    public BigDecimal calculateSalaryForUser(Long userId, LocalDate monthDate) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        // 1. Загальна кількість робочих днів у місяці
//        int workingDays = monthWorkDaysCheckerService.countWorkDayInMonth();
//
//        // 2. Ставка за день
//        BigDecimal salaryPerDay = user.getSalary().divide(BigDecimal.valueOf(workingDays), 2, RoundingMode.HALF_UP);
//
//        // 3. Ставка за годину (8 год/день)
//        BigDecimal salaryPerHour = salaryPerDay.divide(BigDecimal.valueOf(8), 2, RoundingMode.HALF_UP);
//
//        // 4. Отримати пропущені години
//        List<MissingWorkDays> missedDays = missingWorkDaysRepository.findAllByUserIdAndMonth(userId, monthDate);
//        BigDecimal totalMissedHours = missedDays.stream()
//                .map(MissingWorkDays::getMissingHours)
//                .reduce(BigDecimal.ZERO, BigDecimal::add);
//
//        // 5. Отримати овертайми
//        List<OverTimeWork> overtimes = overtimeRepository.findAllByUserIdAndMonth(userId, monthDate);
//        BigDecimal totalOvertimePayment = overtimes.stream()
//                .map(o -> o.getOvertime_hours().multiply(o.getMultiplier()))
//                .reduce(BigDecimal.ZERO, BigDecimal::add)
//                .multiply(salaryPerHour);
//
//        // 6. Мінус пропущені години
//        BigDecimal deduction = totalMissedHours.multiply(salaryPerHour);
//
//        // 7. Результат
//        BigDecimal finalSalary = user.getSalary().subtract(deduction).add(totalOvertimePayment);
//        return finalSalary.setScale(2, RoundingMode.HALF_UP);
//    }
//}

}
