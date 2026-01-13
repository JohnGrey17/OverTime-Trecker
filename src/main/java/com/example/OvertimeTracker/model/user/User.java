    package com.example.OvertimeTracker.model.user;

    import com.example.OvertimeTracker.model.bonus.Bonus;
    import com.example.OvertimeTracker.model.missingHours.MissingWorkDays;
    import com.example.OvertimeTracker.model.overTimeWork.OverTimeWork;
    import com.example.OvertimeTracker.model.salaryTransaction.SalaryTransaction;
    import com.example.OvertimeTracker.model.department.Department;
    import com.example.OvertimeTracker.model.roles.Role;
    import com.example.OvertimeTracker.model.user.userCondition.UserCondition;
    import jakarta.persistence.*;
    import lombok.Data;
    import org.springframework.security.core.GrantedAuthority;
    import org.springframework.security.core.userdetails.UserDetails;

    import java.math.BigDecimal;
    import java.time.LocalDateTime;
    import java.util.*;

    @Entity
    @Table(name = "users")
    @Data
    public class User implements UserDetails {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String firstName;
        private String lastName;
        private BigDecimal salary;
        private String phoneNumber;

        @OneToMany(
                mappedBy = "user",
                cascade = CascadeType.ALL,
                orphanRemoval = true,
                fetch = FetchType.LAZY
        )
        private List<UserCondition> conditions = new ArrayList<>();

        @ManyToOne
        @JoinColumn(name = "department_id", referencedColumnName = "id")
        private Department department;

        @ManyToMany(fetch = FetchType.LAZY)
        @JoinTable(
                name = "users_roles",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "role_id")
        )
        private Set<Role> roles;

        @Column(nullable = false,unique = true)
        private String email;

        @Column(nullable = false)
        private String password;

        @Column(name = "reset_code_hash")
        private String resetCodeHash;

        @Column(name = "reset_code_expires_at")
        private LocalDateTime resetCodeExpiresAt;

        @Column(name = "reset_code_used_at")
        private LocalDateTime resetCodeUsedAt;

        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private Set<OverTimeWork> overTimeWorks = new HashSet<>();

        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private Set<SalaryTransaction> salaryTransactions = new HashSet<>();

        @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private Set<MissingWorkDays> missingWorkDays = new HashSet<>();

        @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private Set<Bonus> expenses = new HashSet<>();

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return roles;
        }

        @Override
        public String getUsername() {
            return email;
        }

        @Override
        public String getPassword() {
            return password;
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }

