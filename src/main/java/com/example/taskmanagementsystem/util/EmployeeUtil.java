package com.example.taskmanagementsystem.util;

import com.example.taskmanagementsystem.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeUtil {

    public static Employee convert(Employee employee) {
        return Employee.builder()
                .name(employee.getName())
                .role(employee.getRole())
                .email(employee.getEmail())
                .build();
    }
}