    package com.example.OvertimeTracker.model;

    import jakarta.persistence.*;
    import lombok.Data;

    import java.time.LocalDateTime;

    @Entity
    @Data
    @Table(name = "over_time_works")
    public class OverTimeWork {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private LocalDateTime overTimeDate;

        private String description;

        private Double multiplier;

        private Double overtime_hours;

        @ManyToOne
        @JoinColumn(name = "user_id")
        private User user;
    }
