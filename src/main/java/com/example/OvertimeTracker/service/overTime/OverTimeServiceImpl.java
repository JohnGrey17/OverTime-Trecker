package com.example.OvertimeTracker.service.overTime;

import com.example.OvertimeTracker.dto.OvertimeRequestDto;
import com.example.OvertimeTracker.repositories.OvertimeRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OverTimeServiceImpl implements OvertimeTrackerService {

    private final OvertimeRepository overtimeRepository;
    private final com.example.OvertimeTracker.factory.WorkEntityFactory workEntityFactory;

    @Override
    public void addNewOvertime(OvertimeRequestDto requestDto, Long userId) {

        val overtimeWork = workEntityFactory.createOvertimeWork(requestDto, userId);
        overtimeRepository.save(overtimeWork);
    }
}
