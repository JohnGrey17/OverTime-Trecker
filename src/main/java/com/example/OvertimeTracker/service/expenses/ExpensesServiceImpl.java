package com.example.OvertimeTracker.service.expenses;

import com.example.OvertimeTracker.dto.expenses.ExpensesResponseDto;
import com.example.OvertimeTracker.dto.expenses.ExpensesRequestDto;
import com.example.OvertimeTracker.model.Expense;
import com.example.OvertimeTracker.repositories.ExpensesRepository;
import com.example.OvertimeTracker.service.factory.DtoFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpensesServiceImpl implements ExpensesService {

    private final com.example.OvertimeTracker.factory.WorkEntityFactory workEntityFactory;
    private final ExpensesRepository expensesRepository;
    private final DtoFactory dtoFactory;

    @Override
    public String addNewExpense(Long userId, ExpensesRequestDto requestDto) {

        Expense expense = workEntityFactory.createExpense(requestDto, userId);
        expensesRepository.save(expense);
        return "Saved was success";
    }

    @Override
    public List<ExpensesResponseDto> getAllByUserIdAndMonth(Long userId, int year, int month) {

        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        return expensesRepository.findAllByUser_IdAndDateBetween(userId, start, end)
                .stream()
                .map(dtoFactory::createExpenseResponseFro)
                .toList();
    }
}
