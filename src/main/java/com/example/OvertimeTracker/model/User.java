    package com.example.OvertimeTracker.model;

    import jakarta.persistence.*;
    import lombok.Data;

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
    }

