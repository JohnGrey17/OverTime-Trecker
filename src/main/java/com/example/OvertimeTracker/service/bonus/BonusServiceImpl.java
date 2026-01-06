package com.example.OvertimeTracker.service.bonus;

import com.example.OvertimeTracker.dto.bonus.BonusResponseDto;
import com.example.OvertimeTracker.dto.bonus.BonusRequestDto;
import com.example.OvertimeTracker.model.bonus.Bonus;
import com.example.OvertimeTracker.repositories.BonusRepository;
import com.example.OvertimeTracker.service.factory.DtoFactory;

import com.example.OvertimeTracker.service.factory.WorkEntityFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BonusServiceImpl implements BonusService {

    private final WorkEntityFactory workEntityFactory;
    private final BonusRepository bonusRepository;
    private final DtoFactory dtoFactory;

    @Override
    public String createBonus(Long userId, BonusRequestDto requestDto) {

        Bonus bonus = workEntityFactory.createBonus(requestDto, userId);
        bonusRepository.save(bonus);
        return "Saved was success";
    }

    @Override
    public List<BonusResponseDto> getAllByUserIdAndMonth(Long userId, int year, int month) {

        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        return bonusRepository.findAllByUser_IdAndDateBetween(userId, start, end)
                .stream()
                .map(dtoFactory::createExpenseResponseFro)
                .toList();
    }
}
