package com.example.OvertimeTracker.controller;

import com.example.OvertimeTracker.dto.expenses.ExpensesResponseDto;
import com.example.OvertimeTracker.dto.expenses.ExpensesRequestDto;
import com.example.OvertimeTracker.model.user.User;
import com.example.OvertimeTracker.service.expenses.ExpensesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/expense")
@Tag(name = "Expense", description = "Controller for managing user's expenses")
public class ExpenseController {

    private final ExpensesService expensesService;

    @PostMapping("/add")
    @Operation(summary = "Add a Expenses",
            description = "Creates a record for a Expenses with a reason and sum")
    @PreAuthorize("hasRole('USER')")
    public String addNewExpenses(
            @RequestBody ExpensesRequestDto requestDto, @AuthenticationPrincipal User user) {
        return expensesService.addNewExpense(user.getId(), requestDto);

    }

    @GetMapping("/getBy/month")
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Get all Expenses",
            description = "Get all Expenses")
    public List<ExpensesResponseDto> getAllByUserIdAndPeriod(@RequestParam int year, int month, @AuthenticationPrincipal User user) {
        return expensesService.getAllByUserIdAndMonth(user.getId(),year,month);
    }
}
