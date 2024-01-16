package com.example.taskmanagementsystem.service;

import com.example.taskmanagementsystem.entity.Employee;
import com.example.taskmanagementsystem.exceptions.EmployeeListEmptyException;
import com.example.taskmanagementsystem.exceptions.EmployeeNotFoundException;
import com.example.taskmanagementsystem.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void testReadAll() {
        when(employeeRepository.findAll()).thenReturn(Arrays.asList(new Employee(), new Employee()));

        List<Employee> employees = employeeService.findAll();

        assertFalse(employees.isEmpty());
        assertEquals(2, employees.size());
    }

    @Test
    void testReadAllEmptyList() {
        when(employeeRepository.findAll()).thenReturn(Collections.emptyList());

        assertThrows(EmployeeListEmptyException.class, () -> employeeService.findAll());
    }

    @Test
    void testGetEmployeeById() {
        Long employeeId = 1L;
        Employee mockEmployee = new Employee();

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(mockEmployee));

        Employee result = employeeService.getEmployeeById(employeeId);

        assertNotNull(result);
        assertEquals(mockEmployee, result);
    }

    @Test
    void testGetEmployeeByIdNotFound() {
        Long employeeId = 1L;
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundException.class, () -> employeeService.getEmployeeById(employeeId));
    }

    @Test
    void testDeleteByname() {
        String employeeName = "John";
        employeeService.deleteByname(employeeName);

        verify(employeeRepository, times(1)).deleteByname(employeeName);
    }

    @Test
    void testDeleteById() {
        Long employeeId = 1L;
        Employee mockEmployee = new Employee();

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(mockEmployee));

        employeeService.deleteById(employeeId);

        verify(employeeRepository, times(1)).findById(employeeId);
        verify(employeeRepository, times(1)).delete(mockEmployee);
    }

    @Test
    void testDeleteByIdNotFound() {
        Long employeeId = 1L;


        when(employeeRepository.findById(employeeId)).thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundException.class, () -> employeeService.deleteById(employeeId));


        verify(employeeRepository, times(1)).findById(employeeId);
        verify(employeeRepository, never()).delete(any());
    }
}