package com.example.OvertimeTracker.service.user;

import com.example.OvertimeTracker.dto.user.UserResponseDto;
import com.example.OvertimeTracker.exceptions.types.UserException;
import com.example.OvertimeTracker.model.user.Department;
import com.example.OvertimeTracker.model.user.User;
import com.example.OvertimeTracker.repositories.UserRepository;
import com.example.OvertimeTracker.service.factory.DtoFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final DtoFactory dtoFactory;

    @Override
    public List<UserResponseDto> findAllByDepartment(String departmentName) {
        Department dep = Department.valueOf(departmentName); // перетворення String -> Enum
        return userRepository.findByDepartmentName(departmentName)
                .stream()
                .map(dtoFactory::createUserResponseDto)
                .toList();
    }

    @Override
    public UserResponseDto findUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserException("User does not exist"));
        return dtoFactory.createUserResponseDto(user);
    }
}
