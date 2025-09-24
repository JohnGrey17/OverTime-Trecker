    package com.example.OvertimeTracker.service.missingWorkDays;

    import com.example.OvertimeTracker.dto.missingDate.MissingDayResponseDto;
    import com.example.OvertimeTracker.dto.missingDate.MissingWorkDateRequestDto;
    import com.example.OvertimeTracker.repositories.MissingWorkDaysRepository;
    import com.example.OvertimeTracker.service.factory.DtoFactory;
    import lombok.RequiredArgsConstructor;
    import lombok.val;
    import org.springframework.stereotype.Service;

    import java.time.LocalDate;
    import java.util.List;

    @Service
    @RequiredArgsConstructor
    public class MissingWorkDaysServiceImpl implements MissingWorkDaysService {

        private final MissingWorkDaysRepository missingWorkDaysRepository;

        private final com.example.OvertimeTracker.factory.WorkEntityFactory workEntityFactory;

        private final DtoFactory dtoFactory;

        @Override
        public void addMissingWorkDay(MissingWorkDateRequestDto requestDto, Long userId) {

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
    }
