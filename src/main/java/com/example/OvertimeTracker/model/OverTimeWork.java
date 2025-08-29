    package com.example.OvertimeTracker.model;

    import jakarta.persistence.*;
    import lombok.Data;

    import java.math.BigDecimal;
    import java.time.LocalDate;

    @Entity
    @Data
    @Table(name = "over_time_works")
    public class OverTimeWork {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private LocalDate overTimeDateRegistration;

        private String description;

        private BigDecimal multiplier;

        private BigDecimal overtime_hours;

        @ManyToOne
        @JoinColumn(name = "user_id")
        private User user;
    }
