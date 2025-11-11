package com.example.OvertimeTracker.controller.user.admin;

import com.example.OvertimeTracker.dto.overTime.OverTimeResponseDto;
import com.example.OvertimeTracker.service.overTime.OvertimeTrackerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/overtimes")
@RequiredArgsConstructor
@Tag(name = "Admin: Overtime", description = "Admin endpoints to view user overtime records.")
public class OverTimeWorkControllerAdmin {

    private final OvertimeTrackerService overtimeTrackerService;

    @Operation(summary = "Get user overtime", description = "Returns all overtime records for a user in a given month and year.")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public List<OverTimeResponseDto> getOvertimesByUserAndPeriod(
            @RequestParam Long userId,
            @RequestParam int year,
            @RequestParam int month) {
        return overtimeTrackerService.getAllByMonthAndUserId(userId, year, month);
    }
}
