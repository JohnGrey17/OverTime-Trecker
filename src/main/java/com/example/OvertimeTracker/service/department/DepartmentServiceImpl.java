package com.example.OvertimeTracker.service.department;

import com.example.OvertimeTracker.dto.department.DepartmentResponseDto;
import com.example.OvertimeTracker.dto.department.admin.DepartmentUpdateRequestDto;
import com.example.OvertimeTracker.dto.department.admin.NewDepartmentRequestDto;
import com.example.OvertimeTracker.exceptions.types.DepartmentException;
import com.example.OvertimeTracker.model.department.Department;
import com.example.OvertimeTracker.repositories.DepartmentRepository;
import com.example.OvertimeTracker.service.factory.DtoFactory;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    private final DtoFactory dtoFactory;

    private final com.example.OvertimeTracker.factory.WorkEntityFactory workEntityFactory;

    @Override
    public List<DepartmentResponseDto> getAllDepartments() {
        return departmentRepository.findAll().stream()
                .map(dtoFactory::createDepartmentResponseDto)
                .toList();
    }

    @Override
    public void createNewDepartment(NewDepartmentRequestDto requestDto) {
        workEntityFactory.createDepartment(requestDto);
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
}
