package com.example.OvertimeTracker.model.department;

import com.example.OvertimeTracker.model.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "departments")
@Getter
@Setter
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true, nullable = false)
    private String code;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private Set<User> users = new HashSet<>();

}
