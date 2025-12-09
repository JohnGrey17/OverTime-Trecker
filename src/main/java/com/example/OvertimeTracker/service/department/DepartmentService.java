package com.example.OvertimeTracker.service.department;

import com.example.OvertimeTracker.dto.department.DepartmentResponseDto;
import com.example.OvertimeTracker.dto.department.admin.DepartmentUpdateRequestDto;
import com.example.OvertimeTracker.dto.department.admin.NewDepartmentRequestDto;

import java.util.List;

public interface DepartmentService {

    List<DepartmentResponseDto> getAllDepartments();

    void createNewDepartment(NewDepartmentRequestDto requestDto);

    void updateDepartmentInfo(DepartmentUpdateRequestDto requestDto);

}
