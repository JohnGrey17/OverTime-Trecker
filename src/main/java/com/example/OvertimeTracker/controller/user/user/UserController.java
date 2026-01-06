package com.example.OvertimeTracker.controller.user.user;

import com.example.OvertimeTracker.dto.user.update.user.UserPasswordUpdateRequestDto;
import com.example.OvertimeTracker.dto.user.update.user.UserUpdateRequestDto;
import com.example.OvertimeTracker.dto.user.userResponse.UserResponseDto;
import com.example.OvertimeTracker.model.user.User;
import com.example.OvertimeTracker.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
@Tag(name = "User Controller",description = "Thi controller give possibility to USER do own user card")
public class UserController {
    private final UserService userService;

    @PostMapping("/update-card")
    @Operation(description = "That controller give possibility to change user card info")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> updateCardInfo(
            @AuthenticationPrincipal User user,
            @RequestBody UserUpdateRequestDto requestDto) {
        userService.updateUserCardInfo(user.getId(), requestDto);
        return ResponseEntity.ok("User card updated successfully");
    }


    @PostMapping("/update-pass")
    @Operation(description = "That controller give possibility to change user password")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> updateUserPassword(
            @AuthenticationPrincipal User user,
            @RequestBody UserPasswordUpdateRequestDto requestDto ) {

        userService.changePassword(user.getId(), requestDto);
        return ResponseEntity.ok("User password updated successfully");

    }

    @GetMapping ("/getOwn")
    @Operation(description = "That controller give possibility to get own info")
    @PreAuthorize("hasRole('USER')")
    public UserResponseDto getOwnInfo(
            @AuthenticationPrincipal User user) {
       return userService.getOwnInfo(user.getId());
    }

}
