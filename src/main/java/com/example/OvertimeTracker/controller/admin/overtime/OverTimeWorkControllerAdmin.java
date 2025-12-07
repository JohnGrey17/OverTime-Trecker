package com.example.OvertimeTracker.controller.admin.overtime;

import com.example.OvertimeTracker.dto.overTime.OverTimeResponseDto;
import com.example.OvertimeTracker.service.overTime.OvertimeTrackerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequiredArgsConstructor
@RequestMapping("/over-time")

@Tag(name = "Over time controller",description = "That controller allow admin get all user`s overtime by parameters")
public class OverTimeWorkControllerAdmin {

    private final OvertimeTrackerService overtimeTrackerService;

    @GetMapping("/getBy")
    @PreAuthorize("hasRole('ADMIN')")
    public List<OverTimeResponseDto> getAllByUserIdAndPeriod(
            @RequestParam int year,
            @RequestParam int month,
            @RequestParam Long userId) {
        return overtimeTrackerService.getAllByMonthAndUserId(userId, year, month);
    }
}
