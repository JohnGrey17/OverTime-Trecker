    package com.example.OvertimeTracker.service.missingWorkDays;

    import com.example.OvertimeTracker.dto.missingDate.MissingDayResponseDto;
    import com.example.OvertimeTracker.dto.missingDate.MissingDayUpdateRequestDto;
    import com.example.OvertimeTracker.dto.missingDate.MissingWorkDateRequestDto;
    import com.example.OvertimeTracker.dto.overTime.OverTimeUpdateRequestDto;
    import com.example.OvertimeTracker.exceptions.types.MissingDayException;
    import com.example.OvertimeTracker.exceptions.types.MissingWorkDayValidationException;
    import com.example.OvertimeTracker.model.MissingWorkDays;
    import com.example.OvertimeTracker.model.OverTimeWork;
    import com.example.OvertimeTracker.repositories.MissingWorkDaysRepository;
    import com.example.OvertimeTracker.service.factory.DtoFactory;
    import com.example.OvertimeTracker.service.factory.validator.WorkTimeRulesService;
    import com.example.OvertimeTracker.service.workDayConflict.WorkDayConflictService;
    import lombok.RequiredArgsConstructor;
    import lombok.val;
    import org.springframework.stereotype.Service;

    import java.time.LocalDate;
    import java.util.List;
    import java.util.Optional;

    @Service
    @RequiredArgsConstructor
    public class MissingWorkDaysServiceImpl implements MissingWorkDaysService {

        private final MissingWorkDaysRepository missingWorkDaysRepository;

        private final com.example.OvertimeTracker.factory.WorkEntityFactory workEntityFactory;

        private final DtoFactory dtoFactory;
        private final WorkDayConflictService workDayConflictService;
        private final WorkTimeRulesService workTimeRulesService;

        @Override
        public void addMissingWorkDay(MissingWorkDateRequestDto requestDto, Long userId) {

            LocalDate date = requestDto.getDate();

            workDayConflictService.validateOvertimeCreation(userId, date);


            if (missingWorkDaysRepository.existsByUserIdAndDate(userId, date)) {
                throw new MissingWorkDayValidationException(
                        "Missing work day already exists for this date: " + date
                );
            }

            val missingWorkDay = workEntityFactory.createMissingWorkDay(requestDto, userId);
            missingWorkDaysRepository.save(missingWorkDay);
        }

        @Override
        public List<MissingDayResponseDto> getAllByMonth(Long userId, int year , int month) {
            LocalDate start = LocalDate.of(year, month, 1);
            LocalDate end = start.withDayOfMonth(start.lengthOfMonth());


            return missingWorkDaysRepository.findAllByUser_IdAndDateBetween(userId, start, end)
                    .stream()
                    .map(dtoFactory::createMissingDayResponseDto)
                    .toList();
        }

        @Override
        public List<MissingDayResponseDto> getAllByMonthAndUserId(Long userId, int year, int month) {
            LocalDate start = LocalDate.of(year, month, 1);
            LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

            return missingWorkDaysRepository.findAllByUser_IdAndDateBetween(userId, start, end)
                    .stream()
                    .map(dtoFactory::createMissingDayResponseDto)
                    .toList();
        }

        @Override
        public void updateMissingDay(Long userId, MissingDayUpdateRequestDto requestDto) {

            MissingWorkDays missingWorkDays = missingWorkDaysRepository
                    .findByIdAndUserId(requestDto.getId(), userId).orElseThrow(
                    () -> new MissingDayException("Cannot find Missing day time item for this user"));
            workTimeRulesService.validateMissingWorkDay(missingWorkDays.getDate(),requestDto.getMissingHours());
            applyOverTimeUpdates(missingWorkDays, requestDto);
            missingWorkDaysRepository.save(missingWorkDays);

        }

        @Override
        public void deleteMissingDay(Long userId, Long missingHourId) {
            MissingWorkDays missingWorkDays = missingWorkDaysRepository
                    .findByIdAndUserId(missingHourId, userId).orElseThrow(
                            () -> new MissingDayException("Cannot find Missing day time item for this user"));
            missingWorkDaysRepository.delete(missingWorkDays);
        }

        private void applyOverTimeUpdates(MissingWorkDays entity, MissingDayUpdateRequestDto dto) {

            if (dto.getReason() != null && !dto.getReason().isBlank()) {
                entity.setReason(dto.getReason());
            }

            if (dto.getMissingHours() != null) {
                entity.setMissingHours(dto.getMissingHours());
            }
        }

    }
