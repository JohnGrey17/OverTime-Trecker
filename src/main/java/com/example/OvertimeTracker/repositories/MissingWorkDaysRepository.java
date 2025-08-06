package com.example.OvertimeTracker.repositories;

import com.example.OvertimeTracker.model.MissingWorkDays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MissingWorkDaysRepository extends JpaRepository<MissingWorkDays, Long> {
}
