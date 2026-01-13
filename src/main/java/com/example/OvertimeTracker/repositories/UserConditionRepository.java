package com.example.OvertimeTracker.repositories;

import com.example.OvertimeTracker.model.user.userCondition.UserCondition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserConditionRepository extends JpaRepository<UserCondition, Long> {

    List<UserCondition> findByUserId(Long userId);
    Optional<UserCondition> findFirstByUserIdAndActiveTrueOrderByPriorityDescIdDesc(Long userId);


}
