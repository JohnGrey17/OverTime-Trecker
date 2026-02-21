package com.example.OvertimeTracker.repositories;

import com.example.OvertimeTracker.model.department.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    boolean existsByCode(String code);

    List<Department> findAllByParent_Id(Long parentId);

    boolean existsByParent_Id(Long parentId);

    Optional<Department> findByIdAndParent_Id(Long id, Long parentId);


}
