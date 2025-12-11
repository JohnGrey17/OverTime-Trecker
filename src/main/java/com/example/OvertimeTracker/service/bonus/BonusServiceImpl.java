package com.example.OvertimeTracker.service.bonus;

import com.example.OvertimeTracker.dto.bonus.BonusResponseDto;
import com.example.OvertimeTracker.dto.bonus.BonusRequestDto;
import com.example.OvertimeTracker.dto.bonus.BonusUpdateRequestDto;
import com.example.OvertimeTracker.exceptions.types.BonusException;
import com.example.OvertimeTracker.model.Bonus;
import com.example.OvertimeTracker.repositories.BonusRepository;
import com.example.OvertimeTracker.service.factory.DtoFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BonusServiceImpl implements BonusService {

    private final com.example.OvertimeTracker.factory.WorkEntityFactory workEntityFactory;
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
                .map(dtoFactory::createBonusResponseFro)
                .toList();
    }

    @Override
    public void deleteBonus(Long bonusId, Long userId) {
        Bonus bonus = bonusRepository.findByIdAndUserId(bonusId, userId).orElseThrow(
                () -> new BonusException("Can`t find bonus"));
        bonusRepository.deleteById(bonus.getId());

    }

    @Override
    public void updateBonus(BonusUpdateRequestDto requestDto, Long userId, Long bonusId) {
        Bonus bonus = bonusRepository.findByIdAndUserId(bonusId, userId).orElseThrow(
                () -> new BonusException("Can`t find bonus"));

        validateAndUpdate(requestDto, bonus);
        bonusRepository.save(bonus);
    }

    private void validateAndUpdate(BonusUpdateRequestDto updateRequestDto, Bonus bonus) {
        if (!updateRequestDto.getReason().isBlank() || !updateRequestDto.getReason().isEmpty()) {
            bonus.setReason(updateRequestDto.getReason());
        } else {
            throw new BonusException("Reason could not be empty");
        }
        BigDecimal sum = updateRequestDto.getBonusSum();
        if (sum != null && sum.compareTo(BigDecimal.ZERO) > 0) {
            bonus.setSum(sum);
        } else {
            throw new BonusException("Sum should be bigger than 0");
        }


    }
}
