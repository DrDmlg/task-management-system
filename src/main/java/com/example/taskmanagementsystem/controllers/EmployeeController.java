package com.example.taskmanagementsystem.controllers;

import com.example.taskmanagementsystem.entity.Employee;
import com.example.taskmanagementsystem.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<Employee>> readAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(employeeService.readAll());
    }

    @DeleteMapping("/delete/{name}")
    public HttpStatus deleteByname(@PathVariable String name) {
        employeeService.deleteByname(name);
        return HttpStatus.OK;
    }

    @DeleteMapping("/delete/{id}")
    public HttpStatus deleteById(@PathVariable Long id) {
        employeeService.deleteById(id);
        return HttpStatus.OK;
    }

    @PostMapping
    public HttpStatus create(@RequestBody Employee employee) {
        employeeService.create(employee);
        return HttpStatus.OK;
    }
}