package com.example.OvertimeTracker.service.codeGeneratorService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class GeneratorCodeServiceImpl implements GeneratorCodeService {

    @Override
    public String generateVerificationCode() {
        SecureRandom random = new SecureRandom();
        int code = random.nextInt(1_000_000); // 0..999999
        return String.format("%06d", code);
    }


}