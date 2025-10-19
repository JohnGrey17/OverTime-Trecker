package com.example.OvertimeTracker.model;

import com.example.OvertimeTracker.model.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "expenses")
@Getter
@Setter
@ToString
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private String reason;

    private BigDecimal sum;

    private String filePath;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
