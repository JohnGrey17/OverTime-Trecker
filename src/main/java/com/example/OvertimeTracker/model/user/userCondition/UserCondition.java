package com.example.OvertimeTracker.model.user.userCondition;

import com.example.OvertimeTracker.model.user.User;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Table(name = "user_condition")
@Entity
@Data
public class UserCondition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // FK column: user_id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private UserConditionType type;



    @Column( precision = 19, scale = 6)
    private BigDecimal amount;

    @Column()
    private int priority = 0;

    @Column(nullable = false)
    private boolean active = true;
}
