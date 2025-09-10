package com.example.OvertimeTracker.mapper;

import com.example.OvertimeTracker.config.MapperConfig;
import com.example.OvertimeTracker.dto.user.UserRegistrationRequestDto;
import com.example.OvertimeTracker.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(config = MapperConfig.class, uses = {DepartmentMapper.class})
public interface UserMapper {

    @Mapping(source = "departmentName", target = "departmentName", qualifiedByName = "stringToDepartmentSet")
    User toModel(UserRegistrationRequestDto requestDto);

}
