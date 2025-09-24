package com.example.OvertimeTracker.repositories;

import com.example.OvertimeTracker.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
   Optional<User> findUserByPhoneNumber(String phoneNumber);

   Optional<User> findByEmail(String email);

    Optional<User> findByDepartmentName(String departmentName);
}
