package com.example.OvertimeTracker.controller;

import com.example.OvertimeTracker.service.salaryCounter.SalaryCounterService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;


//@RestController
@RequestMapping("/count")
@RequiredArgsConstructor
public class SalaryCounterController {


    private final SalaryCounterService salaryCounterService;

//    @GetMapping("/get")
//    @PreAuthorize("hasRole('USER')")
//    public BigDecimal addOverTime(@RequestBody SalaryCounterRequestDto requestDto, @AuthenticationPrincipal User user) {
//        return salaryCounterService.calculateSalaryForUser(user.getId(), requestDto.getDate());
//    }
}
