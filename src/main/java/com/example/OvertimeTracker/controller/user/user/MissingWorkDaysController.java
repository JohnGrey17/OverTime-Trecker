package com.example.OvertimeTracker.controller.user.user;

import com.example.OvertimeTracker.dto.missingDate.*;
import com.example.OvertimeTracker.model.user.User;
import com.example.OvertimeTracker.service.missingWorkDays.MissingWorkDaysService;
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
@RequestMapping("/user/missing-days")
@Tag(name = "User: Missing Work Days", description = "Manage user's missing workday records.")
public class MissingWorkDaysController {

    private final MissingWorkDaysService missingWorkDaysService;

    @Operation(summary = "Add missing workday", description = "Records a new missing workday for the authenticated user.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('USER')")
    public void addMissingWorkDate(
            @RequestBody MissingWorkDateRequestDto requestDto,
            @AuthenticationPrincipal User user) {
        missingWorkDaysService.addMissingWorkDay(requestDto, user.getId());
    }

    @Operation(summary = "Get user missing days", description = "Fetches all missing workdays by month and year for the authenticated user.")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('USER')")
    public List<MissingDayResponseDto> getAllByUserIdAndPeriod(
            @RequestParam int year,
            @RequestParam int month,
            @AuthenticationPrincipal User user) {
        return missingWorkDaysService.getAllByMonth(user.getId(), year, month);
    }
}
