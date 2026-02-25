package com.example.OvertimeTracker.service.department;

import com.example.OvertimeTracker.dto.department.admin.SubDepartmentCreateRequestDto;
import com.example.OvertimeTracker.dto.department.user.department.DepartmentResponseDto;
import com.example.OvertimeTracker.dto.department.admin.DepartmentUpdateRequestDto;
import com.example.OvertimeTracker.dto.department.admin.NewDepartmentRequestDto;
import com.example.OvertimeTracker.dto.department.user.subdepartment.SubDepartmentResponseDto;

import java.util.List;

public interface DepartmentService {

    List<DepartmentResponseDto> getAllDepartments();

    void createNewDepartment(NewDepartmentRequestDto requestDto);

    void updateDepartmentInfo(DepartmentUpdateRequestDto requestDto);

    List<SubDepartmentResponseDto> getAllSubDepartment(Long subDepartmentId);

    void createSubDepartment(Long parentId, SubDepartmentCreateRequestDto dto);

    void deleteDepartment(Long departmentId);

}
