package com.example.OvertimeTracker.controller;

import com.example.OvertimeTracker.dto.missingDate.MissingDayMonthRequestDto;
import com.example.OvertimeTracker.dto.missingDate.MissingDayResponseDto;
import com.example.OvertimeTracker.dto.missingDate.MissingWorkDateRequestDto;
import com.example.OvertimeTracker.model.user.User;
import com.example.OvertimeTracker.service.missingWorkDays.MissingWorkDaysService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missing-hours")
@Tag(name = "Missing Workdays", description = "Controller for managing user's missed workdays")
public class MissingWorkDaysController {

    private final MissingWorkDaysService missingWorkDaysService;

    @PostMapping("/add")
    @Operation(summary = "Add a missed workday",
            description = "Creates a record for a missed workday with a reason and number of missing hours")
    @PreAuthorize("hasRole('USER')")
    public void addMissingWorkDate(
            @RequestBody MissingWorkDateRequestDto requestDto, @AuthenticationPrincipal User user) {
        missingWorkDaysService.addMissingWorkDay(requestDto, user.getId());
    }

    @GetMapping("/getBy/month")
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Get all missing days by months",
            description = "Get all missiть ng days by months")
    public List<MissingDayResponseDto> getAllByUserIdAndPeriod(@RequestParam int year, int month, @AuthenticationPrincipal User user) {
        return missingWorkDaysService.getAllByMonth(user.getId() ,year, month);
    }
}
