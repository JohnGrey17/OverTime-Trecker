    package com.example.OvertimeTracker.model.user;

    import com.example.OvertimeTracker.model.MissingWorkDays;
    import com.example.OvertimeTracker.model.OverTimeWork;
    import com.example.OvertimeTracker.model.SalaryTransaction;
    import com.example.OvertimeTracker.model.roles.Role;
    import jakarta.persistence.*;
    import lombok.Data;
    import org.springframework.security.core.GrantedAuthority;
    import org.springframework.security.core.userdetails.UserDetails;

    import java.math.BigDecimal;
    import java.util.Collection;
    import java.util.HashSet;
    import java.util.Set;

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

        @Enumerated(EnumType.STRING)
        private Set<Department> departmentName;

        @ManyToMany(fetch = FetchType.EAGER)
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

        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private Set<OverTimeWork> overTimeWorks = new HashSet<>();

        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private Set<SalaryTransaction> salaryTransactions = new HashSet<>();

        @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private Set<MissingWorkDays> missingWorkDays = new HashSet<>();

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

