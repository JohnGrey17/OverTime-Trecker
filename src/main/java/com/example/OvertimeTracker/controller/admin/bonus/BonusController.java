package com.example.OvertimeTracker.controller.admin.bonus;

import com.example.OvertimeTracker.dto.bonus.BonusResponseDto;
import com.example.OvertimeTracker.dto.bonus.BonusRequestDto;
import com.example.OvertimeTracker.dto.bonus.BonusUpdateRequestDto;
import com.example.OvertimeTracker.model.user.User;
import com.example.OvertimeTracker.service.bonus.BonusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bonus")
@Tag(name = "Bonus", description = "Controller for ADMIN managing user's bonuses")
public class BonusController {

    private final BonusService bonusService;

    @PostMapping("/add")
    @Operation(summary = "Add a Bonus",
            description = "Creates a record for a Bonus with a reason and sum")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public String createBonus(
            @RequestBody BonusRequestDto requestDto, @RequestParam Long userId) {
        return bonusService.createBonus(userId, requestDto);
    }

    @GetMapping("/getBy/month")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get all Bonus",
            description = "Get all Bonus")
    public List<BonusResponseDto> getAllByUserIdAndPeriod(
            @RequestParam int year, @RequestParam int month,
            @RequestParam Long userId) {
        return bonusService.getAllByUserIdAndMonth(userId,year,month);
    }

    @PostMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update specified user`s bonus",
            description = "Allows ADMIN to update specified user`s bonus")
    public ResponseEntity<Void> updateBonus(
            @RequestBody BonusUpdateRequestDto requestDto,
            @RequestParam Long userId,
            @RequestParam Long bonusId) {

        bonusService.updateBonus(requestDto, userId, bonusId);
        return ResponseEntity.ok().build();

    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete specified user`s bonus",
            description = "Allows ADMIN to delete specified user`s bonus")
    public ResponseEntity<Void> deleteBonus(
            @RequestParam Long bonusId,
            @RequestParam Long userId
    ) {
        bonusService.deleteBonus(bonusId, userId);
        return ResponseEntity.noContent().build();
    }
}
