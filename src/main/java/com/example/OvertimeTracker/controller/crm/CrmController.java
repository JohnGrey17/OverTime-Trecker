package com.example.OvertimeTracker.controller.crm;

import com.example.OvertimeTracker.dto.user.UserCrmWithAllCount;
import com.example.OvertimeTracker.service.crm.CrmService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/crm")
@RequiredArgsConstructor
@Tag(name = "CRM Analytics", description = "Provides analytical and aggregated data for departments and users.")
public class CrmController {

    private final CrmService crmService;

    @Operation(
            summary = "Get department analytics",
            description = "Returns aggregated data for all users in a department (overtime, missing days, salary) for the selected month and year."
    )
    @GetMapping("/departments/{departmentId}/users")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserCrmWithAllCount> getUsersByDepartment(
            @PathVariable Long departmentId,
            @RequestParam int year,
            @RequestParam int month) {
        return crmService.getAllUsersByDepartmentsId(departmentId, year, month);
    }
}
