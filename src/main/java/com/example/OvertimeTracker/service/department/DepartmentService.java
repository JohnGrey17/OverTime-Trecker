package com.example.OvertimeTracker.service.department;

import com.example.OvertimeTracker.dto.DepartmentResponseDto;

import java.util.List;

public interface DepartmentService {

    List<DepartmentResponseDto> getAllDepartments();
}
