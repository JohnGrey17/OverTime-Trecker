package com.example.OvertimeTracker.model.user;

import lombok.Getter;

@Getter
public enum Department {
    DEPARTMENT_1("Черепашки-ніндзя"),
    DEPARTMENT_2("R.E.D"),
    DEPARTMENT_3("Неко-Неко"),
    DEPARTMENT_4("Соколи");

    private final String displayName;

    Department(String displayName) {
        this.displayName = displayName;
    }
}
