package com.example.OvertimeTracker.service.crm;

import com.example.OvertimeTracker.dto.user.UserCrmWithAllCount;

import java.util.List;

public interface CrmService {

    List<UserCrmWithAllCount> getAllUsersByDepartmentsId(Long departmentId, int year, int month);
}
