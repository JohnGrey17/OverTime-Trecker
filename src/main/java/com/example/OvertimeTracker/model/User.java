    package com.example.OvertimeTracker.model;

    import jakarta.persistence.*;
    import lombok.Data;

    import java.math.BigDecimal;
    import java.util.HashSet;
    import java.util.Set;

    @Entity
    @Table(name = "users")
    @Data
    public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String firstName;
        private String lastName;
        private BigDecimal Salary;
        private String phoneNumber;

        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private Set<OverTimeWork> overTimeWorks = new HashSet<>();

        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private Set<SalaryTransaction> salaryTransactions = new HashSet<>();
    }

