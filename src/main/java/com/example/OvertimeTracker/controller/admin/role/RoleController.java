package com.example.OvertimeTracker.controller.admin.role;

import com.example.OvertimeTracker.dto.roles.RoleResponseDto;
import com.example.OvertimeTracker.service.role.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequiredArgsConstructor
@RequestMapping("/role")

@Tag(name = "Role controller",description = "That controller allow admin get action with role")
public class RoleController {

    private final RoleService roleService;

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(description = "That controller give all roles information")
    public List<RoleResponseDto> getRoles() {
        return roleService.getAll();
    }

    

}
