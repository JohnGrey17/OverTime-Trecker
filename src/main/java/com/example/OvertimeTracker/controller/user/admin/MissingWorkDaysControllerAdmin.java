package com.example.OvertimeTracker.controller.user.admin;

import com.example.OvertimeTracker.dto.missingDate.MissingDayResponseDto;
import com.example.OvertimeTracker.service.missingWorkDays.MissingWorkDaysService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/missing-days")
@RequiredArgsConstructor
@Tag(name = "Admin: Missing Work Days", description = "Admin endpoints to view missing workdays for users.")
public class MissingWorkDaysControllerAdmin {

    private final MissingWorkDaysService missingWorkDaysService;

    @Operation(summary = "Get user missing days", description = "Fetches all missing days for a user by given year and month.")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public List<MissingDayResponseDto> getMissingDaysByUserAndPeriod(
            @RequestParam Long userId,
            @RequestParam int year,
            @RequestParam int month) {
        return missingWorkDaysService.getAllByMonthAndUserId(userId, year, month);
    }
}
