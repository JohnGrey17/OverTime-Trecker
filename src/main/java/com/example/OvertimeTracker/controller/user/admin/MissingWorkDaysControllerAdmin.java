package com.example.OvertimeTracker.controller.user.admin;

import com.example.OvertimeTracker.dto.missingDate.MissingDayResponseDto;
import com.example.OvertimeTracker.dto.overTime.OverTimeResponseDto;
import com.example.OvertimeTracker.service.missingWorkDays.MissingWorkDaysService;
import com.example.OvertimeTracker.service.overTime.OvertimeTrackerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequiredArgsConstructor
@RequestMapping("/missing-hours")
@Tag(name = "Missing work days",description = "That controller allow admin get all user`s missing work days by parameters")
public class MissingWorkDaysControllerAdmin {

    private final MissingWorkDaysService missingWorkDaysService;

    @GetMapping("/getBy")
    @PreAuthorize("hasRole('ADMIN')")
    public List<MissingDayResponseDto> getAllByUserIdAndPeriod(
            @RequestParam int year,
            @RequestParam int month,
            @RequestParam Long userId) {
        return missingWorkDaysService.getAllByMonthAndUserId(userId, year, month);
    }
}
