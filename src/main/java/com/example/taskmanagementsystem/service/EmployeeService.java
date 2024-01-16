package com.example.taskmanagementsystem.service;

import com.example.taskmanagementsystem.entity.Employee;
import com.example.taskmanagementsystem.exceptions.EmployeeListEmptyException;
import com.example.taskmanagementsystem.exceptions.EmployeeNotFoundException;
import com.example.taskmanagementsystem.exceptions.ErrorDeletingEmployee;
import com.example.taskmanagementsystem.repository.EmployeeRepository;
import com.example.taskmanagementsystem.util.EmployeeUtil;
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
        if (employees.isEmpty()) throw new EmployeeListEmptyException();
        return employees;
    }

    public Employee getEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId).orElseThrow(EmployeeNotFoundException::new);
    }

    @Transactional
    public void deleteByname(String name) {
        employeeRepository.deleteByname(name);
    }

    @Transactional
    public void deleteById(Long id) {
        Employee employeeById = getEmployeeById(id);

        try {
            employeeRepository.delete(employeeById);
        } catch (Exception e) {
            throw new ErrorDeletingEmployee();
        }
    }

    public void create(Employee employee) {
        Employee convert = EmployeeUtil.convert(employee);
        employeeRepository.save(convert);
    }
}