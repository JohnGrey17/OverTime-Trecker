package com.example.OvertimeTracker.controller.admin;

import com.example.OvertimeTracker.dto.expenses.ExpensesResponseDto;
import com.example.OvertimeTracker.dto.missingDate.MissingDayResponseDto;
import com.example.OvertimeTracker.service.expenses.ExpensesService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/expense")
@Tag(name = "Expenses controller for Admin", description = "That controller give possibility act with expenses")
@RequiredArgsConstructor
public class ExpensesControllerAdmin {

    private final ExpensesService expensesService;

    @GetMapping("/getBy")
    @PreAuthorize("hasRole('ADMIN')")
    public List<ExpensesResponseDto> getAllByUserIdAndPeriod(
            @RequestParam int year,
            @RequestParam int month,
            @RequestParam Long userId) {
        return expensesService.getAllByUserIdAndMonth(userId, year, month);
    }
}
