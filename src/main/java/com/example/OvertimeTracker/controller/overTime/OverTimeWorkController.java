package com.example.OvertimeTracker.controller.overTime;

import com.example.OvertimeTracker.dto.overTime.OverTimeResponseDto;
import com.example.OvertimeTracker.dto.overTime.OverTimeUpdateRequestDto;
import com.example.OvertimeTracker.dto.overTime.OvertimeCreateRequestDto;
import com.example.OvertimeTracker.model.user.User;
import com.example.OvertimeTracker.service.overTime.OvertimeTrackerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/over-time")
public class OverTimeWorkController {

    private final OvertimeTrackerService overtimeTrackerService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('USER')")
    public void addOverTime(@RequestBody OvertimeCreateRequestDto requestDto, @AuthenticationPrincipal User user) {
        overtimeTrackerService.addNewOvertime(requestDto, user.getId());
    }

    @GetMapping("/getBy/month")
    @PreAuthorize("hasRole('USER')")
    public List<OverTimeResponseDto> getAllByUserIdAndPeriod(
            @RequestParam int year,
            @RequestParam int month,
            @AuthenticationPrincipal User user) {
        return overtimeTrackerService.getAllByMonth(user.getId(),year, month);
    }

    @PostMapping("/update")
    @PreAuthorize("hasRole('USER')")
    public String updateOverTime(Long overTimeId, OverTimeUpdateRequestDto requestDto) {
return null;
    }


}
