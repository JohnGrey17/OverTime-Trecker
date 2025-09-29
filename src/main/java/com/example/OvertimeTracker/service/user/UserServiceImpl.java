package com.example.OvertimeTracker.service.user;

import com.example.OvertimeTracker.dto.user.UserResponseDto;
import com.example.OvertimeTracker.exceptions.types.UserException;
import com.example.OvertimeTracker.model.user.User;
import com.example.OvertimeTracker.repositories.UserRepository;
import com.example.OvertimeTracker.service.factory.DtoFactory;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final DtoFactory dtoFactory;

    @Override
    @Transactional
    public List<UserResponseDto> getUsersByDepartment(Long departmentId) {
        List<User> byDepartmentId = userRepository.findByDepartmentId(departmentId);
        return byDepartmentId.stream()
                .map(dtoFactory::createUserResponseDto)
                .toList();
    }

    @Override
    public UserResponseDto getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserException("User does not exist"));
        return dtoFactory.createUserResponseDto(user);
    }

    //TODO додати знайти користувачів по департменту , знайти користувача по номеру телефона , дістати всю інфу про овертайми  в календар по користувачові , дістати список всіх оверів та місів на відділ , перевірка ролей  і у адміна свій інтерфейс
}
