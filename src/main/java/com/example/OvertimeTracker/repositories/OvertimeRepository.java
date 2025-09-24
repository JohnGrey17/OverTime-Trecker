package com.example.OvertimeTracker.repositories;

import com.example.OvertimeTracker.model.OverTimeWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OvertimeRepository extends JpaRepository<OverTimeWork, Long> {

    List<OverTimeWork> findAllByUser_IdAndOverTimeDateRegistrationBetween(
            Long userId, LocalDate startDate, LocalDate endDate);
}
