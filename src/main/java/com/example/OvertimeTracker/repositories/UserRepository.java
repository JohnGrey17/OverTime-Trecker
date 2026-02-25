package com.example.OvertimeTracker.repositories;

import com.example.OvertimeTracker.model.user.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByPhoneNumber(String phoneNumber);

    @EntityGraph(attributePaths = "roles")
    Optional<User> findByEmail(String email);


    @Query("SELECT u FROM User u " +
            "LEFT JOIN FETCH u.department " +
            "LEFT JOIN FETCH u.roles " +
            "WHERE u.department.id = :departmentId")
    List<User> findByDepartmentId(Long departmentId);

    boolean existsByDepartment_Id(Long departmentId);

}
