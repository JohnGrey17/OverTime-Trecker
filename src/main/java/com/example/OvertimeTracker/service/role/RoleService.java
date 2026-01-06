package com.example.OvertimeTracker.service.role;

import com.example.OvertimeTracker.dto.roles.RoleResponseDto;

import java.util.List;

public interface RoleService {

    List<RoleResponseDto> getAll();
}
