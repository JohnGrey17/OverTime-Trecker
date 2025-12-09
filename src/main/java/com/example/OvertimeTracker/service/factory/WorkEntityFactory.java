package com.example.OvertimeTracker.factory;

import com.example.OvertimeTracker.dto.department.admin.NewDepartmentRequestDto;
import com.example.OvertimeTracker.dto.expenses.ExpensesRequestDto;
import com.example.OvertimeTracker.dto.missingDate.MissingWorkDateRequestDto;
import com.example.OvertimeTracker.dto.overTime.OvertimeCreateRequestDto;
import com.example.OvertimeTracker.exceptions.types.DepartmentException;
import com.example.OvertimeTracker.exceptions.types.UserException;
import com.example.OvertimeTracker.model.Expense;
import com.example.OvertimeTracker.model.MissingWorkDays;
import com.example.OvertimeTracker.model.OverTimeWork;
import com.example.OvertimeTracker.model.department.Department;
import com.example.OvertimeTracker.model.user.User;
import com.example.OvertimeTracker.repositories.DepartmentRepository;
import com.example.OvertimeTracker.repositories.UserRepository;
import com.example.OvertimeTracker.service.factory.validator.WorkTimeRulesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.DecapsulateException;
import java.math.BigDecimal;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class WorkEntityFactory {

    private final UserRepository userRepository;
    private final WorkTimeRulesService workTimeRulesService;

    private final DepartmentRepository departmentRepository;

    public MissingWorkDays createMissingWorkDay(MissingWorkDateRequestDto dto, Long userId) {
        User user = getUser(userId);

        LocalDate date = dto.getDate();
        BigDecimal missingHours = dto.getMissingHours();

        workTimeRulesService.validateMissingWorkDay(date, missingHours);

        MissingWorkDays entity = new MissingWorkDays();
        entity.setDate(date);
        entity.setReason(dto.getReason());
        entity.setMissingHours(missingHours);
        entity.setUser(user);
        return entity;
    }

    public OverTimeWork createOvertimeWork(OvertimeCreateRequestDto dto, Long userId) {
        User user = getUser(userId);

        LocalDate date = dto.getOverTimeDateRegistration();
        BigDecimal overtimeHours = dto.getOvertime_hours();

        workTimeRulesService.validateOvertimeHours(date, overtimeHours);

        OverTimeWork entity = new OverTimeWork();
        entity.setOverTimeDateRegistration(date);
        entity.setDescription(dto.getDescription());
        entity.setOvertimeHours(overtimeHours);
        entity.setMultiplier(workTimeRulesService.calculateMultiplier(date));
        entity.setUser(user);
        return entity;
    }

    public Expense createExpense(ExpensesRequestDto requestDto, Long userId) {
        User user = getUser(userId);
        Expense expense = new Expense();
        expense.setDate(requestDto.getDate());
        expense.setReason(requestDto.getReason());
        expense.setSum(requestDto.getSum());
        expense.setUser(user);
        return expense;
    }

    public Department createDepartment(NewDepartmentRequestDto requestDto) {
        Department department = new Department();
        if (requestDto.getDepartmentName().isBlank()) {
            throw new DepartmentException("Department name can not be Blank");
        }

        if (requestDto.getDepartmentCode().isBlank()) {
            throw new DepartmentException("Department code can not be Blank");

        }
        department.setName(requestDto.getDepartmentName());
        department.setCode(requestDto.getDepartmentCode());
        departmentRepository.save(department);
        return department;
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserException("User with that id does not exist"));
    }
}
