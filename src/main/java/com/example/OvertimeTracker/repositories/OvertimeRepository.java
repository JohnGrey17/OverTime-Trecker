package com.example.OvertimeTracker.repositories;

import com.example.OvertimeTracker.model.OverTimeWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OvertimeRepository extends JpaRepository<OverTimeWork, Long> {
}
