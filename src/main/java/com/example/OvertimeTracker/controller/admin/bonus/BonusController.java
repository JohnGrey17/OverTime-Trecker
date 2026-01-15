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
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public String createBonus(
            @RequestParam Long userId,
            @RequestBody BonusRequestDto requestDto
    ) {
        return bonusService.createBonus(userId, requestDto);
    }


    @GetMapping("/getBy/month")
    @PreAuthorize("hasRole('ADMIN')")
    public List<BonusResponseDto> getAllByUserIdAndPeriod(
            @RequestParam Long userId,
            @RequestParam int year,
            @RequestParam int month
    ) {
        return bonusService.getAllByUserIdAndMonth(userId, year, month);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBonus(
            @RequestParam Long userId,
            @RequestParam Long bonusId
    ) {
        bonusService.deleteBonus(userId, bonusId);
    }

    @PostMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public void updateBonus(
            @RequestParam Long userId,
            @RequestParam Long bonusId,
            @RequestBody BonusRequestDto requestDto
    ) {
        bonusService.updateBonus(userId, bonusId, requestDto);
    }


}
