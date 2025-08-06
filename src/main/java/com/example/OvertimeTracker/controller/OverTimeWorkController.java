package com.example.OvertimeTracker.controller;

import com.example.OvertimeTracker.dto.OvertimeRequestDto;
import com.example.OvertimeTracker.service.overTime.OvertimeTrackerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/over-time")
public class OverTimeWorkController {

    private final OvertimeTrackerService overtimeTrackerService;

    @PostMapping("/add")
    public void addOverTime(@RequestBody OvertimeRequestDto requestDto) {
        overtimeTrackerService.addNewOvertime(requestDto);
    }


}
