package com.example.OvertimeTracker.controller.admin.department;

import com.example.OvertimeTracker.dto.department.admin.DepartmentUpdateRequestDto;
import com.example.OvertimeTracker.dto.department.admin.NewDepartmentRequestDto;
import com.example.OvertimeTracker.dto.department.admin.SubDepartmentCreateRequestDto;
import com.example.OvertimeTracker.service.department.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/department")
@Tag(name = "Department Controller",description = "This controller give possibility to ADMIN do act with department")
public class AdminDepartmentController {

    private final DepartmentService departmentService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(description = "Create new department")
    public ResponseEntity<String> createNewDepartment(@RequestBody NewDepartmentRequestDto requestDto) {
        departmentService.createNewDepartment(requestDto);
        return ResponseEntity.ok("New department was created");

    }

    @PostMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(description = "Update department department")
    public ResponseEntity<String> updateDepartmentInfo(@RequestBody DepartmentUpdateRequestDto requestDto) {
        departmentService.updateDepartmentInfo(requestDto);
        return ResponseEntity.ok("Department info was changed");

    }

    @PostMapping("/{parentId}/children")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(
            summary = "Create sub-department",
            description = "Creates a new child department inside the specified parent department."
    )
    public ResponseEntity<Void> createSubDepartment(@PathVariable Long parentId,
                                                    @RequestBody SubDepartmentCreateRequestDto requestDto) {
        departmentService.createSubDepartment(parentId, requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
    @DeleteMapping("/{departmentId}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(
            summary = "Delete department",
            description = "Deletes a department. Deletion is blocked if the department has sub-departments or assigned users."
    )
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long departmentId) {
        departmentService.deleteDepartment(departmentId);
        return ResponseEntity.noContent().build(); // 204
    }
}
