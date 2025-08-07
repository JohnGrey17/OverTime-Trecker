    package com.example.OvertimeTracker.service.missingWorkDays;

    import com.example.OvertimeTracker.dto.MissingWorkDateRequestDto;
    import com.example.OvertimeTracker.repositories.MissingWorkDaysRepository;
    import lombok.RequiredArgsConstructor;
    import lombok.val;
    import org.springframework.stereotype.Service;

    @Service
    @RequiredArgsConstructor
    public class MissingWorkDaysServiceImpl implements MissingWorkDaysService {

        private final MissingWorkDaysRepository missingWorkDaysRepository;

        private final com.example.OvertimeTracker.factory.WorkEntityFactory workEntityFactory;

        @Override
        public void addMissingWorkDay(MissingWorkDateRequestDto requestDto, Long userId) {

            val missingWorkDay = workEntityFactory.createMissingWorkDay(requestDto, userId);
            missingWorkDaysRepository.save(missingWorkDay);
        }
    }
