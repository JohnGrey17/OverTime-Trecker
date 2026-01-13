package com.example.OvertimeTracker.service.user.userCondition;

import com.example.OvertimeTracker.dto.user.userCondition.CreateUserConditionRequestDto;
import com.example.OvertimeTracker.dto.user.userCondition.UpdateUserConditionRequestDto;
import com.example.OvertimeTracker.dto.user.userCondition.UserConditionResponseDto;
import com.example.OvertimeTracker.model.user.User;
import com.example.OvertimeTracker.model.user.userCondition.UserCondition;
import com.example.OvertimeTracker.model.user.userCondition.UserConditionType;
import com.example.OvertimeTracker.repositories.UserConditionRepository;
import com.example.OvertimeTracker.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserConditionServiceImpl implements UserConditionService {

    private final UserConditionRepository userConditionRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<UserConditionResponseDto> getByUser(Long userId) {
        return userConditionRepository.findByUserId(userId).stream()
                .sorted(Comparator.comparingInt(UserCondition::getPriority).reversed())
                .map(this::toDto)
                .toList();
    }

    @Override
    @Transactional
    public UserConditionResponseDto create(Long userId, CreateUserConditionRequestDto request) {
        validateCreate(request);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));

        UserCondition condition = new UserCondition();
        condition.setUser(user);

        // у нас 1 тип, але лишаємо як є
        condition.setType(request.getType());

        condition.setAmount(request.getAmount());
        condition.setPriority(request.getPriority() != null ? request.getPriority() : 0);
        condition.setActive(request.getActive() != null ? request.getActive() : true);

        validateByType(condition);

        UserCondition saved = userConditionRepository.save(condition);
        return toDto(saved);
    }

    @Override
    @Transactional
    public UserConditionResponseDto update(Long userId, Long conditionId, UpdateUserConditionRequestDto request) {
        if (request == null) throw new IllegalArgumentException("Request is null");

        UserCondition condition = userConditionRepository.findById(conditionId)
                .orElseThrow(() -> new IllegalArgumentException("Condition not found: " + conditionId));

        if (!condition.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("Condition " + conditionId + " does not belong to user " + userId);
        }

        if (request.getType() != null) condition.setType(request.getType());
        if (request.getAmount() != null) condition.setAmount(request.getAmount());
        if (request.getPriority() != null) condition.setPriority(request.getPriority());
        if (request.getActive() != null) condition.setActive(request.getActive());

        validateByType(condition);

        UserCondition saved = userConditionRepository.save(condition);
        return toDto(saved);
    }

    @Override
    @Transactional
    public void delete(Long userId, Long conditionId) {
        UserCondition condition = userConditionRepository.findById(conditionId)
                .orElseThrow(() -> new IllegalArgumentException("Condition not found: " + conditionId));

        if (!condition.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("Condition " + conditionId + " does not belong to user " + userId);
        }

        userConditionRepository.delete(condition);
    }

    // ===== validation =====

    private void validateCreate(CreateUserConditionRequestDto request) {
        if (request == null) throw new IllegalArgumentException("Request is null");
        if (request.getType() == null) throw new IllegalArgumentException("type is required");
        if (request.getType() != UserConditionType.FIXED_PER_OVERTIME) {
            throw new IllegalArgumentException("Only FIXED_PER_HOUR is supported");
        }
        validateAmount(request.getAmount());
    }

    private void validateByType(UserCondition c) {
        if (c.getType() == null) throw new IllegalArgumentException("type is required");
        if (c.getType() != UserConditionType.FIXED_PER_OVERTIME) {
            throw new IllegalArgumentException("Only FIXED_PER_HOUR is supported");
        }
        validateAmount(c.getAmount());
    }

    private void validateAmount(BigDecimal amount) {
        if (amount == null) throw new IllegalArgumentException("amount is required");
        if (amount.signum() <= 0) throw new IllegalArgumentException("amount must be > 0");
    }

    private UserConditionResponseDto toDto(UserCondition c) {
        UserConditionResponseDto dto = new UserConditionResponseDto();
        dto.setId(c.getId());
        dto.setUserId(c.getUser() != null ? c.getUser().getId() : null);
        dto.setType(c.getType());
        dto.setAmount(c.getAmount());
        dto.setPriority(c.getPriority());
        dto.setActive(c.isActive());
        return dto;
    }
}
