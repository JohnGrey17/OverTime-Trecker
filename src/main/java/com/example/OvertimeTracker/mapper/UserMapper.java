package com.example.OvertimeTracker.mapper;

import com.example.OvertimeTracker.config.MapperConfig;
import com.example.OvertimeTracker.dto.user.UserRegistrationRequestDto;
import com.example.OvertimeTracker.model.User;
import org.mapstruct.Mapper;


@Mapper(config = MapperConfig.class)
public interface UserMapper {
    User toModel(UserRegistrationRequestDto requestDto);

}
