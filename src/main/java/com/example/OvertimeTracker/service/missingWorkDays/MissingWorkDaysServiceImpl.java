package com.example.OvertimeTracker.service.missingWorkDays;

import com.example.OvertimeTracker.dto.MissingWorkDateRequestDto;
import com.example.OvertimeTracker.model.MissingWorkDays;
import com.example.OvertimeTracker.model.User;
import com.example.OvertimeTracker.repositories.MissingWorkDaysRepository;
import com.example.OvertimeTracker.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class MissingWorkDaysServiceImpl implements MissingWorkDaysService {

    private final MissingWorkDaysRepository missingWorkDaysRepository;

    private final UserRepository userRepository;

    @Override
    public void addMissingWorkDay(MissingWorkDateRequestDto requestDto) {

        MissingWorkDays missingWorkDays = new MissingWorkDays();
        missingWorkDays.setMissingHours(requestDto.getMissingHours());
        missingWorkDays.setReason(requestDto.getReason());
        missingWorkDays.setDate(requestDto.getDate());
        missingWorkDays.setUser(createUser());
        missingWorkDaysRepository.save(missingWorkDays);
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
