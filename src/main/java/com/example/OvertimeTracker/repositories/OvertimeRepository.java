package com.example.OvertimeTracker.repositories;

import com.example.OvertimeTracker.model.OverTimeWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface OvertimeRepository extends JpaRepository<OverTimeWork, Long> {

    boolean existsByUserIdAndOverTimeDateRegistration(Long userId, LocalDate overTimeDateRegistration);

    List<OverTimeWork> findAllByUser_IdAndOverTimeDateRegistrationBetween(
            Long userId, LocalDate startDate, LocalDate endDate);

    Optional<OverTimeWork> findByIdAndUserId(Long missingHourId, Long userId);

}
