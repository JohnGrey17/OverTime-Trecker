package com.example.OvertimeTracker.repositories;

import com.example.OvertimeTracker.model.MissingWorkDays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MissingWorkDaysRepository extends JpaRepository<MissingWorkDays, Long> {
    List<MissingWorkDays> findAllByUser_IdAndDateBetween(
            Long userId, LocalDate startDate, LocalDate endDate);

    boolean existsByUserIdAndDate(Long userId, LocalDate date);

    Optional<MissingWorkDays> findByIdAndUserId (Long missingDayId, Long userId);
 }
