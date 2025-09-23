package com.example.OvertimeTracker.service.overTime;

import com.example.OvertimeTracker.dto.overTime.OverTimeResponseDto;
import com.example.OvertimeTracker.dto.overTime.OvertimeCreateRequestDto;
import com.example.OvertimeTracker.repositories.OvertimeRepository;
import com.example.OvertimeTracker.service.factory.DtoFactory;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OverTimeServiceImpl implements OvertimeTrackerService {

    private final OvertimeRepository overtimeRepository;
    private final DtoFactory dtoFactory;
    private final com.example.OvertimeTracker.factory.WorkEntityFactory workEntityFactory;

    @Override
    public void addNewOvertime(OvertimeCreateRequestDto requestDto, Long userId) {

        val overtimeWork = workEntityFactory.createOvertimeWork(requestDto, userId);
        overtimeRepository.save(overtimeWork);
    }

    public List<OverTimeResponseDto> getAllByMonth(Long userId, int year, int month) {
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        List<OverTimeResponseDto> list = overtimeRepository.findAllByUserIdAndOverTimeDateRegistrationBetween(userId, start, end)
                .stream()
                .map(dtoFactory::createOverTimeResponseDto)
                .toList();
        System.out.println(list);
        return list;
    }

}
