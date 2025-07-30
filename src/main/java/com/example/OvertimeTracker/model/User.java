    package com.example.OvertimeTracker.model;

    import jakarta.persistence.*;
    import lombok.Data;

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
        private String departmentName;
        private String position;
        private String phoneNumber;

        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private Set<OverTimeWork> overTimeWorks = new HashSet<>();
    }

