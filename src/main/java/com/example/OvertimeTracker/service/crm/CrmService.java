package com.example.OvertimeTracker.service.crm;

import com.example.OvertimeTracker.dto.user.UserOverMissingResponseDto;

import java.util.List;

public interface CrmService {

    List<UserOverMissingResponseDto> getAllUsersByDepartmentsId(Long departmentId, int year, int month);
}
