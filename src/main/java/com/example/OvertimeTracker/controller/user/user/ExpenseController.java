package com.example.OvertimeTracker.controller.user.user;

import com.example.OvertimeTracker.dto.expenses.*;
import com.example.OvertimeTracker.model.user.User;
import com.example.OvertimeTracker.service.expenses.ExpensesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/expenses")
@Tag(name = "User: Expenses", description = "Manage personal expense records.")
public class ExpenseController {

    private final ExpensesService expensesService;

    @Operation(summary = "Add expense", description = "Creates a new expense record for the authenticated user.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('USER')")
    public String addNewExpenses(@RequestBody ExpensesRequestDto requestDto, @AuthenticationPrincipal User user) {
        return expensesService.addNewExpense(user.getId(), requestDto);
    }

    @Operation(summary = "Get user expenses", description = "Retrieves all expense records for the current user by month and year.")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('USER')")
    public List<ExpensesResponseDto> getAllByUserIdAndPeriod(
            @RequestParam int year,
            @RequestParam int month,
            @AuthenticationPrincipal User user) {
        return expensesService.getAllByUserIdAndMonth(user.getId(), year, month);
    }
}
