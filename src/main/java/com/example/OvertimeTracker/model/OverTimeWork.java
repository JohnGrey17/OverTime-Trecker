    package com.example.OvertimeTracker.model;

    import com.example.OvertimeTracker.model.user.User;
    import jakarta.persistence.*;
    import lombok.Data;
    import lombok.Getter;
    import lombok.Setter;
    import lombok.ToString;

    import java.math.BigDecimal;
    import java.time.LocalDate;

    @Entity
    @Getter
    @Setter
    @ToString
    @Table(name = "over_time_works")
    public class OverTimeWork {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private LocalDate overTimeDateRegistration;

        private String description;

        private BigDecimal multiplier;

        @Column(name = "overtime_hours")
        private BigDecimal overtimeHours;

        @ManyToOne
        @JoinColumn(name = "user_id")
        private User user;
    }
