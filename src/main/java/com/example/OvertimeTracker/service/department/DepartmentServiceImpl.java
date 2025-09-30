package com.example.OvertimeTracker.service.department;

import com.example.OvertimeTracker.dto.DepartmentResponseDto;
import com.example.OvertimeTracker.repositories.DepartmentRepository;
import com.example.OvertimeTracker.service.factory.DtoFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    private final DtoFactory dtoFactory;

    @Override
    public List<DepartmentResponseDto> getAllDepartments() {
        return departmentRepository.findAll().stream()
                .map(dtoFactory::createDepartmentResponseDto)
                .toList();
    }
}
