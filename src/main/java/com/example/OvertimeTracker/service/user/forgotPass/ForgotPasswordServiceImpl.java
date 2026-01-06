package com.example.OvertimeTracker.service.user.forgotPass;

import com.example.OvertimeTracker.exceptions.types.UserException;
import com.example.OvertimeTracker.model.user.User;
import com.example.OvertimeTracker.repositories.UserRepository;
import com.example.OvertimeTracker.service.codeGeneratorService.GeneratorCodeService;
import com.example.OvertimeTracker.service.email.EmailSenderService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ForgotPasswordServiceImpl  implements ForgotPasswordService {


    private final GeneratorCodeService generatorCodeService;
    private final EmailSenderService emailSenderService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private static final int CODE_TTL_MINUTES = 10;

    @Override
    public void sendResetCode(String userEmail) {
        userRepository.findByEmail(userEmail).ifPresent(user -> {
            String code = generatorCodeService.generateVerificationCode();

            user.setResetCodeHash(passwordEncoder.encode(code));
            user.setResetCodeExpiresAt(LocalDateTime.now().plusMinutes(CODE_TTL_MINUTES));
            user.setResetCodeUsedAt(null);

            userRepository.save(user);

            emailSenderService.sentTempCode(userEmail, code);
        });
    }

    @Override
    public void confirmReset(String email, String code, String newPassword, String confirmPassword) {
        if (!newPassword.equals(confirmPassword)) {
            throw new UserException("Passwords do not match");
        }

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserException("Invalid code or email"));

        if (user.getResetCodeHash() == null || user.getResetCodeExpiresAt() == null) {
            throw new UserException("Invalid code or email");
        }

        if (user.getResetCodeUsedAt() != null) {
            throw new UserException("Code already used");
        }

        if (user.getResetCodeExpiresAt().isBefore(LocalDateTime.now())) {
            throw new UserException("Code expired");
        }

        if (!passwordEncoder.matches(code, user.getResetCodeHash())) {
            throw new UserException("Invalid code or email");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetCodeUsedAt(LocalDateTime.now());


         user.setResetCodeHash(null);

        userRepository.save(user);
    }
}