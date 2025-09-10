package com.example.OvertimeTracker.model;

import com.example.OvertimeTracker.model.user.User;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "salary_transactions")
@Data
public class SalaryTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount; // сума виплати

    private LocalDate startDate; // з якого дня розрахунок
    private LocalDate endDate;   // по який день включно

    private LocalDate transactionDate; // дата коли виплатили

    private String calculatedBy; // хто натискав "порахувати ЗП"

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
