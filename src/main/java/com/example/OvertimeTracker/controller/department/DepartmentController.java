package com.example.OvertimeTracker.controller.department;

import com.example.OvertimeTracker.dto.DepartmentResponseDto;
import com.example.OvertimeTracker.service.department.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
@Tag(name = "Departments", description = "Endpoints for retrieving department information.")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Operation(summary = "List all departments", description = "Returns all departments available in the system.")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DepartmentResponseDto> getAllDepartments() {
        return departmentService.getAllDepartments();
    }
}
