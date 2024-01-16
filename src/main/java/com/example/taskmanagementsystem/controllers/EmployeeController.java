package com.example.taskmanagementsystem.controllers;

import com.example.taskmanagementsystem.entity.Employee;
import com.example.taskmanagementsystem.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@Tag(name = "Employee controller", description = "Endpoints for managing employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    @Operation(summary = "Get all employees", description = "Retrieve a list of all employees.")
    @ApiResponse(responseCode = "200", description = "List of employees successfully retrieved.")
    @ApiResponse(responseCode = "404", description = "No employees found.")
    public ResponseEntity<List<Employee>> readAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(employeeService.readAll());
    }

    @DeleteMapping("/delete/{name}")
    @Operation(summary = "Delete employee by name", description = "Delete an employee by their name.")
    @ApiResponse(responseCode = "200", description = "Employee successfully deleted.")
    @ApiResponse(responseCode = "404", description = "Employee not found.")
    public HttpStatus deleteByname(@PathVariable String name) {
        employeeService.deleteByname(name);
        return HttpStatus.OK;
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete employee by ID", description = "Delete an employee by their ID.")
    @ApiResponse(responseCode = "200", description = "Employee successfully deleted.")
    @ApiResponse(responseCode = "404", description = "Employee not found.")
    public HttpStatus deleteById(@PathVariable Long id) {
        employeeService.deleteById(id);
        return HttpStatus.OK;
    }

    @PostMapping
    @Operation(summary = "Create a new employee", description = "Create a new employee.")
    @ApiResponse(responseCode = "200", description = "Employee created successfully.")
    @ApiResponse(responseCode = "400", description = "Bad request.", content = @Content)
    @ApiResponse(responseCode = "500", description = "Internal server error.", content = @Content)
    public HttpStatus create(@RequestBody Employee employee) {
        employeeService.create(employee);
        return HttpStatus.OK;
    }
}