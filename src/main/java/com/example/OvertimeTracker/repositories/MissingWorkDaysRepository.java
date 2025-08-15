package com.example.OvertimeTracker.repositories;

import com.example.OvertimeTracker.model.MissingWorkDays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MissingWorkDaysRepository extends JpaRepository<MissingWorkDays, Long> {
    @Query("SELECT m FROM MissingWorkDays m WHERE m.user.id = :userId " +
            "AND EXTRACT(MONTH FROM m.date) = :month " +
            "AND EXTRACT(YEAR FROM m.date) = :year")
    List<MissingWorkDays> findAllByUserIdAndMonth(@Param("userId") Long userId,
                                                  @Param("month") int month,
                                                  @Param("year") int year);

}
