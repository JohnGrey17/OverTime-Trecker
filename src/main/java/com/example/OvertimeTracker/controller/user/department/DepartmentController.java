package com.example.OvertimeTracker.controller.user.department;

import com.example.OvertimeTracker.dto.department.user.department.DepartmentResponseDto;
import com.example.OvertimeTracker.dto.department.user.subdepartment.SubDepartmentResponseDto;
import com.example.OvertimeTracker.service.department.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/department")
@RequiredArgsConstructor
@Tag(name = "Department Controller", description = "That controller give possiblility to do act with departments")
public class
DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping("/getAll")
    @Operation(summary = "Get all departments",
            description = "That request we use to get all departments when we register our user")
    public List<DepartmentResponseDto> getAllDepartments() {
       return departmentService.getAllDepartments();
    }

    @GetMapping("/{departmentId}/children")
    @Operation(
            summary = "Get sub-departments of a department",
            description = "Returns all child departments that belong to the specified parent department."
    )
    public List<SubDepartmentResponseDto> getSubDepartments(@PathVariable Long departmentId) {
        return departmentService.getAllSubDepartment(departmentId);
    }

}
