package com.example.OvertimeTracker.repositories;

import com.example.OvertimeTracker.model.Bonus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BonusRepository extends JpaRepository <Bonus, Long> {

    List<Bonus> findAllByUser_IdAndDateBetween(Long userId, LocalDate startDate, LocalDate endDate);

    Optional<Bonus> findByIdAndUserId(Long bonusId, Long userId);
}
