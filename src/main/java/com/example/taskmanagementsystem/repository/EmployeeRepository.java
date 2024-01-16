package com.example.taskmanagementsystem.repository;


import com.example.taskmanagementsystem.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    void deleteByname(String name);
}
