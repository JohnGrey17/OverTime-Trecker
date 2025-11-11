package com.example.OvertimeTracker.controller.user.user;

import com.example.OvertimeTracker.dto.overTime.*;
import com.example.OvertimeTracker.model.user.User;
import com.example.OvertimeTracker.service.overTime.OvertimeTrackerService;
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
@RequestMapping("/user/overtimes")
@Tag(name = "User: Overtime", description = "Manage user's overtime work records.")
public class OverTimeWorkController {

    private final OvertimeTrackerService overtimeTrackerService;

    @Operation(summary = "Add overtime", description = "Creates a new overtime record for the authenticated user.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('USER')")
    public void addOverTime(@RequestBody OvertimeCreateRequestDto requestDto, @AuthenticationPrincipal User user) {
        overtimeTrackerService.addNewOvertime(requestDto, user.getId());
    }

    @Operation(summary = "Get user overtime", description = "Fetches all overtime records for the authenticated user by month and year.")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('USER')")
    public List<OverTimeResponseDto> getAllByUserIdAndPeriod(
            @RequestParam int year,
            @RequestParam int month,
            @AuthenticationPrincipal User user) {
        return overtimeTrackerService.getAllByMonth(user.getId(), year, month);
    }

    @Operation(summary = "Update overtime record", description = "Allows a user to update an existing overtime entry.")
    @PutMapping("/{overTimeId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasRole('USER')")
    public String updateOverTime(
            @PathVariable Long overTimeId,
            @RequestBody OverTimeUpdateRequestDto requestDto) {
        return ""; // TODO implement
    }
}
