package com.example.OvertimeTracker.dto.department.admin;

import jakarta.persistence.Column;

import lombok.Data;

@Data
public class NewDepartmentRequestDto {

    private String departmentName;

    private String departmentCode;
}
