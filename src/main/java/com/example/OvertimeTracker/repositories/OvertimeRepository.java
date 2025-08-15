package com.example.OvertimeTracker.repositories;

import com.example.OvertimeTracker.model.OverTimeWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OvertimeRepository extends JpaRepository<OverTimeWork, Long> {

    @Query("SELECT o FROM OverTimeWork o WHERE o.user.id = :userId " +
            "AND EXTRACT(MONTH FROM o.overTimeDateRegistration) = :month " +
            "AND EXTRACT(YEAR FROM o.overTimeDateRegistration) = :year")
    List<OverTimeWork> findAllByUserIdAndMonth(@Param("userId") Long userId,
                                               @Param("month") int month,
                                               @Param("year") int year);
}
