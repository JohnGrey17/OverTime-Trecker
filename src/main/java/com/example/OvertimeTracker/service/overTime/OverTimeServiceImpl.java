package com.example.OvertimeTracker.service.overTime;

import com.example.OvertimeTracker.dto.OvertimeRequestDto;
import com.example.OvertimeTracker.model.OverTimeWork;
import com.example.OvertimeTracker.model.User;
import com.example.OvertimeTracker.repositories.OvertimeRepository;
import com.example.OvertimeTracker.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;


@Service
@RequiredArgsConstructor
public class OverTimeServiceImpl implements OvertimeTrackerService {

    private final OvertimeRepository overtimeRepository;

    private final UserRepository userRepository;

    @Override
    public void addNewOvertime(OvertimeRequestDto requestDto) {

        OverTimeWork overTimeWork = new OverTimeWork();
        overTimeWork.setDescription(requestDto.getDescription());
        overTimeWork.setOvertime_hours(requestDto.getOvertime_hours());
        overTimeWork.setOverTimeDateRegistration(requestDto.getOverTimeDateRegistration());
        overTimeWork.setMultiplier(dayOfWeekChecker(requestDto.getOverTimeDateRegistration()));
        overTimeWork.setUser(createUser());
        overtimeRepository.save(overTimeWork);
    }

    private BigDecimal dayOfWeekChecker(LocalDate localDate) {
        DayOfWeek day = localDate.getDayOfWeek();

        if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
            return BigDecimal.valueOf(1.5);
        } else {
            return BigDecimal.valueOf(1.0);
        }
    }

    private User createUser() {
        User user = new User();
        user.setFirstName("TEST");
        user.setLastName("TEST");
        user.setSalary(BigDecimal.valueOf(30000));
        user.setPhoneNumber("111111111111");
        userRepository.save(user);
        return user;

        //todo прибрати цей метод
    }
}
//todo зробити валідатор який би не давав наприклад у пятницю вводити перепрацьовки 12 годин