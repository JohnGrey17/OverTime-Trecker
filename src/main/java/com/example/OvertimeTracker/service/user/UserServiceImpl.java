package com.example.OvertimeTracker.service.user;

import com.example.OvertimeTracker.dto.salary.UserUpdateSalaryRequestDto;
import com.example.OvertimeTracker.dto.user.userResponse.UserResponseDto;
import com.example.OvertimeTracker.dto.user.update.user.UserPasswordUpdateRequestDto;
import com.example.OvertimeTracker.dto.user.update.user.UserUpdateRequestDto;
import com.example.OvertimeTracker.exceptions.types.UserException;
import com.example.OvertimeTracker.mapper.UserMapper;
import com.example.OvertimeTracker.model.user.User;
import com.example.OvertimeTracker.repositories.UserRepository;
import com.example.OvertimeTracker.service.factory.DtoFactory;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final DtoFactory dtoFactory;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;


    @Override
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

    @Override
    public String upgradeUserSalary(Long userId, UserUpdateSalaryRequestDto dto) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserException("User does not exist"));
        if (dto.getSalary() != null) {
            user.setSalary(dto.getSalary());
        } else {
            return "Please enter salary";
        }
        userRepository.save(user);

        return "Update saved";
    }

    @Override
    @Transactional
    public void updateUserCardInfo(Long userId, UserUpdateRequestDto requestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException("User does not exist"));

        applyUserCardUpdates(user, requestDto);

        userRepository.save(user);
    }

    @Override
    public void changePassword(Long userId, UserPasswordUpdateRequestDto requestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException("User does not exist"));

        if (!passwordEncoder.matches(
                requestDto.getCurrentPassword(),
                user.getPassword()
        )) {
            throw new UserException("Please insert correct current password");
        }

        if (!requestDto.getNewPassword().equals(requestDto.getRepeatPassword())) {
            throw new UserException("New password and repeatPassword should be same");
        }

        user.setPassword(passwordEncoder.encode(requestDto.getNewPassword()));
        userRepository.save(user);
    }

    @Override
    public UserResponseDto getOwnInfo(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserException("Користува не існує"));
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(userId);
        userResponseDto.setSalary(user.getSalary());
        userResponseDto.setFirstName(user.getFirstName());
        userResponseDto.setLastName(user.getLastName());
        userResponseDto.setPhoneNumber(user.getPhoneNumber());
        userResponseDto.setEmail(user.getEmail());
        return userResponseDto;
    }

    @Override
    public void deleteUser(Long userId, Long userOwnId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new UserException("User not found"));
        if (userId.equals(userOwnId)) {
            throw new UserException("You can`t delete your self from that interface");
        }
        userRepository.deleteById(user.getId());
    }

    private void applyUserCardUpdates(User user, UserUpdateRequestDto dto) {

        if (dto.getFirstName() != null && !dto.getFirstName().isBlank()) {
            validateName(dto.getFirstName(), "First Name");
            user.setFirstName(dto.getFirstName().trim());
        }

        if (dto.getLastName() != null && !dto.getLastName().isBlank()) {
            validateName(dto.getLastName(), "Last Name");
            user.setLastName(dto.getLastName().trim());
        }

        if (dto.getPhoneNumber() != null && !dto.getPhoneNumber().isBlank()) {
            validatePhone(dto.getPhoneNumber());
            user.setPhoneNumber(dto.getPhoneNumber().trim());
        }
    }


    private void validateName(String value, String fieldName) {
        if (value.length() < 2) {
            throw new UserException(fieldName + " must contain at least 2 characters");
        }
    }

    private void validatePhone(String phone) {
        if (!phone.matches("^\\+?[0-9]{10,13}$")) {
            throw new UserException("Invalid phone number format");
        }
    }


    //TODO додати знайти користувачів по департменту , знайти користувача по номеру телефона , дістати всю інфу про овертайми  в календар по користувачові , дістати список всіх оверів та місів на відділ , перевірка ролей  і у адміна свій інтерфейс
}
