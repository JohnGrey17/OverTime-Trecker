package com.example.OvertimeTracker.model;


import jakarta.persistence.*;
import lombok.Data;

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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}