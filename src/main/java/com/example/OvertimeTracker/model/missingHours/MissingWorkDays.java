package com.example.OvertimeTracker.model.missingHours;


import com.example.OvertimeTracker.model.user.User;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "missing_workdays")
@Data
public class MissingWorkDays {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private String reason;

    private BigDecimal missingHours;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}