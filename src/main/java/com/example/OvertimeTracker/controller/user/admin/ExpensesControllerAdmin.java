package com.example.OvertimeTracker.controller.user.admin;

import com.example.OvertimeTracker.dto.expenses.ExpensesResponseDto;
import com.example.OvertimeTracker.service.expenses.ExpensesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/expenses")
@RequiredArgsConstructor
@Tag(name = "Admin: Expenses", description = "Allows administrators to view user expenses by period.")
public class ExpensesControllerAdmin {

    private final ExpensesService expensesService;

    @Operation(
            summary = "Get user expenses",
            description = "Fetches all expense records for a specific user and time period."
    )
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public List<ExpensesResponseDto> getExpensesByUserAndPeriod(
            @RequestParam Long userId,
            @RequestParam int year,
            @RequestParam int month) {
        return expensesService.getAllByUserIdAndMonth(userId, year, month);
    }
}
