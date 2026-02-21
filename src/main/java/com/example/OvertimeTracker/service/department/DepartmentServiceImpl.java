package com.example.OvertimeTracker.service.department;

import com.example.OvertimeTracker.dto.department.admin.SubDepartmentCreateRequestDto;
import com.example.OvertimeTracker.dto.department.user.department.DepartmentResponseDto;
import com.example.OvertimeTracker.dto.department.admin.DepartmentUpdateRequestDto;
import com.example.OvertimeTracker.dto.department.admin.NewDepartmentRequestDto;
import com.example.OvertimeTracker.dto.department.user.subdepartment.SubDepartmentResponseDto;
import com.example.OvertimeTracker.exceptions.types.DepartmentException;
import com.example.OvertimeTracker.exceptions.types.SubDepartmentException;
import com.example.OvertimeTracker.model.department.Department;
import com.example.OvertimeTracker.repositories.DepartmentRepository;
import com.example.OvertimeTracker.repositories.UserRepository;
import com.example.OvertimeTracker.service.factory.DtoFactory;
import com.example.OvertimeTracker.service.factory.WorkEntityFactory;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    private final DtoFactory dtoFactory;

    private final WorkEntityFactory workEntityFactory;

    private final UserRepository userRepository;

    @Override
    public List<DepartmentResponseDto> getAllDepartments() {
        return departmentRepository.findAll().stream()
                .map(dtoFactory::createDepartmentResponseDto)
                .toList();
    }

    @Override
    @Transactional
    public void createNewDepartment(NewDepartmentRequestDto requestDto) {
        departmentRepository.save(workEntityFactory.createNewDepartment(requestDto));
    }

    @Override
    @Transactional
    public void updateDepartmentInfo(DepartmentUpdateRequestDto requestDto) {
        Department department = departmentRepository.findById(requestDto.getDepartmentId())
                .orElseThrow(() -> new DepartmentException("Department not found"));

        if (requestDto.getNewDepartmentName() != null &&
                !requestDto.getNewDepartmentName().isBlank()) {

            department.setName(requestDto.getNewDepartmentName().trim());
        }

        if (requestDto.getNewDepartmentCode() != null &&
                !requestDto.getNewDepartmentCode().isBlank()) {

            boolean exists = departmentRepository
                    .existsByCode(requestDto.getNewDepartmentCode());

            if (exists && !requestDto.getNewDepartmentCode().equals(department.getCode())) {
                throw new RuntimeException("Department with this code already exists");
            }

            department.setCode(requestDto.getNewDepartmentCode().trim());
        }

        departmentRepository.save(department);
    }

    @Override
    public List<SubDepartmentResponseDto> getAllSubDepartment(Long departmentId) {
        return departmentRepository.findAllByParent_Id(departmentId)
                .stream()
                .map(dtoFactory::createSubDepartmentResponseDto)
                .toList();
    }

    @Transactional
    public void createSubDepartment(Long parentId, SubDepartmentCreateRequestDto dto) {
        Department parent = departmentRepository.findById(parentId)
                .orElseThrow(() -> new SubDepartmentException("Parent department not found"));

        Department child = new Department();
        child.setName(dto.getName());
        child.setCode(dto.getCode());
        child.setParent(parent);

        departmentRepository.save(child);
    }

    @Override
    public void deleteDepartment(Long departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new DepartmentException("Department not found: " + departmentId));

        if (userRepository.existsByDepartment_Id(departmentId)) {
            throw new DepartmentException("Cannot delete department. Users are assigned to it.");
        }

        if (departmentRepository.existsByParent_Id(departmentId)) {
            throw new DepartmentException("Cannot delete department. It has sub-departments.");
        }

        departmentRepository.delete(department);
    }
}


