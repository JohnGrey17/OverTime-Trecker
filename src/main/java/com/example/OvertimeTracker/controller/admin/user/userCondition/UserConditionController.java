package com.example.OvertimeTracker.controller.admin.user.userCondition;

import com.example.OvertimeTracker.dto.user.userCondition.CreateUserConditionRequestDto;
import com.example.OvertimeTracker.dto.user.userCondition.UpdateUserConditionRequestDto;
import com.example.OvertimeTracker.dto.user.userCondition.UserConditionResponseDto;
import com.example.OvertimeTracker.service.user.userCondition.UserConditionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-conditions")
@RequiredArgsConstructor
public class UserConditionController {

    private final UserConditionService userConditionService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserConditionResponseDto> getByUser(@RequestParam Long userId) {
        return userConditionService.getByUser(userId);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserConditionResponseDto> create(
            @RequestParam Long userId,
            @RequestBody CreateUserConditionRequestDto request
    ) {
        UserConditionResponseDto dto = userConditionService.create(userId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping("/{conditionId}")
    @PreAuthorize("hasRole('ADMIN')")
    public UserConditionResponseDto update(
            @RequestParam Long userId,
            @PathVariable Long conditionId,
            @RequestBody UpdateUserConditionRequestDto request
    ) {
        return userConditionService.update(userId, conditionId, request);
    }

    @DeleteMapping("/{conditionId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(
            @RequestParam Long userId,
            @PathVariable Long conditionId
    ) {
        userConditionService.delete(userId, conditionId);
        return ResponseEntity.noContent().build();
    }
}
