package com.example.OvertimeTracker.mapper;

import com.example.OvertimeTracker.config.MapperConfig;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class, uses = {DepartmentMapper.class})
public interface UserMapper {


}
