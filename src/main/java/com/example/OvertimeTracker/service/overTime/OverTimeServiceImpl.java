package com.example.OvertimeTracker.service.overTime;

import com.example.OvertimeTracker.dto.overTime.OverTimeResponseDto;
import com.example.OvertimeTracker.dto.overTime.OverTimeUpdateRequestDto;
import com.example.OvertimeTracker.dto.overTime.OvertimeCreateRequestDto;
import com.example.OvertimeTracker.exceptions.types.OverTimeException;
import com.example.OvertimeTracker.exceptions.types.OverTimeValidationException;
import com.example.OvertimeTracker.model.OverTimeWork;
import com.example.OvertimeTracker.repositories.OvertimeRepository;
import com.example.OvertimeTracker.service.factory.DtoFactory;
import com.example.OvertimeTracker.service.factory.validator.WorkTimeRulesService;
import com.example.OvertimeTracker.service.workDayConflict.WorkDayConflictService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OverTimeServiceImpl implements OvertimeTrackerService {

    private final OvertimeRepository overtimeRepository;
    private final DtoFactory dtoFactory;
    private final com.example.OvertimeTracker.factory.WorkEntityFactory workEntityFactory;
    private final WorkDayConflictService workDayConflictService;
    private final WorkTimeRulesService workTimeRulesService;


    @Override
    @Transactional
    public void addNewOvertime(OvertimeCreateRequestDto requestDto, Long userId) {

        LocalDate date = requestDto.getOverTimeDateRegistration();

        workDayConflictService.validateOvertimeCreation(userId, date);

        if (overtimeRepository.existsByUserIdAndOverTimeDateRegistration(userId, date)) {
            throw new OverTimeValidationException(
                    "Overtime for this date already exists: " + date
            );
        }

        val overtimeWork = workEntityFactory.createOvertimeWork(requestDto, userId);
        overtimeRepository.save(overtimeWork);
    }

    @Override
    public List<OverTimeResponseDto> getAllByMonth(Long userId, int year, int month) {
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        return overtimeRepository.findAllByUser_IdAndOverTimeDateRegistrationBetween(userId, start, end)
                .stream()
                .map(dtoFactory::createMissingDayResponseDto)
                .toList();
    }

    @Override
    public List<OverTimeResponseDto> getAllByMonthAndUserId(Long userId, int year, int month) {

        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        return overtimeRepository.findAllByUser_IdAndOverTimeDateRegistrationBetween(userId, start, end)
                .stream()
                .map(dtoFactory::createMissingDayResponseDto)
                .toList();
    }

    @Override
    @Transactional
    public void updateOverTime(Long userId, OverTimeUpdateRequestDto requestDto) {
        OverTimeWork overTime = overtimeRepository.findByIdAndUserId(requestDto.getId(), userId)
                .orElseThrow(() -> new OverTimeException("Cannot find over time item for this user"));

        applyOverTimeUpdates(overTime, requestDto);
        workTimeRulesService.validateOvertimeHours(overTime.getOverTimeDateRegistration(), requestDto.getOvertime_hours());
        overtimeRepository.save(overTime);
    }

    @Override
    public void deleteOverTime(Long userId, Long overTimeId) {
        OverTimeWork overTime = overtimeRepository.findByIdAndUserId(overTimeId, userId)
                .orElseThrow(() -> new OverTimeException("Cannot find over time item for this user"));
        overtimeRepository.delete(overTime);
    }

    private void applyOverTimeUpdates(OverTimeWork entity, OverTimeUpdateRequestDto dto) {

        if (dto.getNewDescription() != null && !dto.getNewDescription().isBlank()) {
            entity.setDescription(dto.getNewDescription());
        }

        if (dto.getOvertime_hours() != null) {
            entity.setOvertimeHours(dto.getOvertime_hours());
        }
    }
}
