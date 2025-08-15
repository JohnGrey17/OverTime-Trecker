package com.example.OvertimeTracker.service.overTime;

import com.example.OvertimeTracker.dto.overTime.OverTimeResponseDto;
import com.example.OvertimeTracker.dto.overTime.OvertimeCreateRequestDto;
import com.example.OvertimeTracker.repositories.OvertimeRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OverTimeServiceImpl implements OvertimeTrackerService {

    private final OvertimeRepository overtimeRepository;
    private final com.example.OvertimeTracker.factory.WorkEntityFactory workEntityFactory;

    @Override
    public void addNewOvertime(OvertimeCreateRequestDto requestDto, Long userId) {

        val overtimeWork = workEntityFactory.createOvertimeWork(requestDto, userId);
        overtimeRepository.save(overtimeWork);
    }

    @Override
    public List<OverTimeResponseDto> getAllByMonth(Long userId, LocalDate month) {
      return null;
    }
}
