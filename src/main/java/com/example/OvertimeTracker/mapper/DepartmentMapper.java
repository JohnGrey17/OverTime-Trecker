
package com.example.OvertimeTracker.mapper;

import com.example.OvertimeTracker.model.user.Department;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class DepartmentMapper {

    @Named("stringToDepartmentSet")
    public Set<Department> mapStringToDepartmentSet(String name) {
        if (name == null || name.isBlank()) return Collections.emptySet();

        return Arrays.stream(Department.values())
                .filter(dep -> dep.getDisplayName().equalsIgnoreCase(name.trim()))
                .findFirst()
                .map(Set::of)
                .orElseThrow(() -> new RuntimeException("❌ Unknown department display name: " + name));
    }
}
