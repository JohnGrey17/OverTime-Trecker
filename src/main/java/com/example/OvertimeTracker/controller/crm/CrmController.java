package com.example.OvertimeTracker.controller.crm;

import com.example.OvertimeTracker.dto.user.userResponse.UserCrmWithAllCount;
import com.example.OvertimeTracker.service.crm.CrmService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/crm")
@Tag(name = "Crm controller", description = "That controller act with crm system")
@RequiredArgsConstructor
public class CrmController {

    private final CrmService crmService;

    @GetMapping("/department")
    @Tag(name = "CRM Controller",
            description = "Provides aggregated user data (overtime, missing days, salary) for each department.")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserCrmWithAllCount> getAllByDepartment(
            @RequestParam Long departmentId,
            @RequestParam int year,
            @RequestParam int month) {
        return crmService.getAllUsersByDepartmentsId(departmentId, year, month);
    }
}
