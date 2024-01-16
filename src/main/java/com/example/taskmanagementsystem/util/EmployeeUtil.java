package com.example.taskmanagementsystem.util;

import com.example.taskmanagementsystem.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeUtil {

    public static Employee convert(Employee employee) {
        Employee newEmployee = new Employee();
        newEmployee.setName(employee.getName());
        newEmployee.setRole(employee.getRole());
        newEmployee.setEmail(employee.getEmail());
        return newEmployee;
    }
}