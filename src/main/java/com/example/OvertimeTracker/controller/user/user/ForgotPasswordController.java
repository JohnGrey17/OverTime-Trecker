package com.example.OvertimeTracker.controller.user.user;

import com.example.OvertimeTracker.dto.password.PasswordResetConfirmDto;
import com.example.OvertimeTracker.dto.password.PasswordResetRequestDto;
import com.example.OvertimeTracker.service.user.forgotPass.ForgotPasswordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/forgot")
public class ForgotPasswordController {

    private final ForgotPasswordService forgotPasswordService;

    @PostMapping("/request")
    public ResponseEntity<Void> request(@Valid @RequestBody PasswordResetRequestDto dto) {
        forgotPasswordService.sendResetCode(dto.getEmail().toLowerCase().trim());

        return ResponseEntity.ok().build();
    }

    @PostMapping("/confirm")
    public ResponseEntity<Void> confirm(@Valid @RequestBody PasswordResetConfirmDto dto) {
        forgotPasswordService.confirmReset(
                dto.getEmail().toLowerCase().trim(),
                dto.getCode(),
                dto.getNewPassword(),
                dto.getConfirmPassword()
        );
        return ResponseEntity.ok().build();
    }
}
