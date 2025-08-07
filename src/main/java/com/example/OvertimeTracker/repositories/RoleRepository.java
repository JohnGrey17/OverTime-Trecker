package com.example.OvertimeTracker.repositories;


import com.example.OvertimeTracker.model.roles.Role;
import com.example.OvertimeTracker.model.roles.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findRoleByName(RoleName name);
}
