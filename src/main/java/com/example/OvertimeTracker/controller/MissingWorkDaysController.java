package com.example.OvertimeTracker.controller;

import com.example.OvertimeTracker.dto.MissingWorkDateRequestDto;
import com.example.OvertimeTracker.service.missingWorkDays.MissingWorkDaysService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missing-hours")
@Tag(name = "Missing Workdays", description = "Controller for managing user's missed workdays")
public class MissingWorkDaysController {

    private final MissingWorkDaysService missingWorkDaysService;

    @PostMapping("/add")
    @Operation(
            summary = "Add a missed workday",
            description = "Creates a record for a missed workday with a reason and number of missing hours"
    )
    public void addMissingWorkDate(@RequestBody MissingWorkDateRequestDto requestDto) {
        missingWorkDaysService.addMissingWorkDay(requestDto);
    }
}
