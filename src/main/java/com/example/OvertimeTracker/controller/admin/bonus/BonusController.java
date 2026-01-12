package com.example.OvertimeTracker.controller.admin.bonus;


import com.example.OvertimeTracker.dto.bonus.BonusRequestDto;
import com.example.OvertimeTracker.dto.bonus.BonusResponseDto;
import com.example.OvertimeTracker.model.user.User;
import com.example.OvertimeTracker.service.bonus.BonusService;
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
@RequestMapping("/bonus")
@Tag(name = "Expense", description = "Controller for managing user's expenses")
public class BonusController {

    private final BonusService bonusService;

    @PostMapping("/add")
    @Operation(summary = "Add a Bonus",
            description = "Creates a record for a Bonus with a reason and sum")
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.CREATED)
    public String createBonus(
            @RequestBody BonusRequestDto requestDto, @AuthenticationPrincipal User user) {
        return bonusService.createBonus(user.getId(), requestDto);



    }

    @GetMapping("/getBy/month")
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Get all Bonus",
            description = "Get all Bonus")
    public List<BonusResponseDto> getAllByUserIdAndPeriod(
            @RequestParam int year, int month,
            @AuthenticationPrincipal User user) {
        return bonusService.getAllByUserIdAndMonth(user.getId(),year,month);
    }

    //todo delete bonus , updateBonus
}
