package com.example.OvertimeTracker.repositories;

import com.example.OvertimeTracker.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpensesRepository extends JpaRepository <Expense, Long> {

    List<Expense> findAllByUser_IdAndDateBetween
            (
            Long userId, LocalDate startDate, LocalDate endDate);

}
