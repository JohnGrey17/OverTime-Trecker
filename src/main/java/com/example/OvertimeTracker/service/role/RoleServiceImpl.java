package com.example.OvertimeTracker.service.role;

import com.example.OvertimeTracker.dto.roles.RoleResponseDto;
import com.example.OvertimeTracker.repositories.RoleRepository;
import com.example.OvertimeTracker.service.factory.DtoFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    private final DtoFactory dtoFactory;

    @Override
    public List<RoleResponseDto> getAll() {
        return roleRepository.findAll().stream().map(dtoFactory::createRoleResponseDto).toList();
    }
}
