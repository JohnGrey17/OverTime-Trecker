package com.example.OvertimeTracker.model.roles;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

@Entity
@ToString
@Getter
@Setter
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false, columnDefinition = "varchar(255)")
    private RoleName name;

    @Column(name = "display_name")
    private String displayName;

    @Override
    public String getAuthority() {
        return "ROLE_" + name.name();
    }
}
