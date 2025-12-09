package com.example.OvertimeTracker.dto.department.admin;

import lombok.Data;

@Data
public class DepartmentUpdateRequestDto {

    private Long departmentId;

    private String newDepartmentName;

    private String newDepartmentCode;

}
