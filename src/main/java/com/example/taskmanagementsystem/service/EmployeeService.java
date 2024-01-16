package com.example.taskmanagementsystem.service;

import com.example.taskmanagementsystem.entity.Employee;

import com.example.taskmanagementsystem.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> readAll() {
        List<Employee> employees = employeeRepository.findAll();
        return employees;
    }

    public Employee getEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId).orElseThrow();
    }
}
