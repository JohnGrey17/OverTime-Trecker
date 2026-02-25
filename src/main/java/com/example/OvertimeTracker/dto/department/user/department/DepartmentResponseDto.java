    package com.example.OvertimeTracker.dto.department.user.department;

    import lombok.Data;

    @Data
    public class DepartmentResponseDto {

        private Long id;

        private String name;

        private String code;
        private Long parentId;
    }
