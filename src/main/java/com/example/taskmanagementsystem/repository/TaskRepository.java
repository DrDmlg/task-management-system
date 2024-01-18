package com.example.taskmanagementsystem.repository;

import com.example.taskmanagementsystem.entity.Task;
import com.example.taskmanagementsystem.enums.Role;
import com.example.taskmanagementsystem.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query(value = "SELECT t FROM task t JOIN employee e ON t.author.id = e.id WHERE e.name = :name and e.role = :role")
    List<Task> findAllTasksByNameAndRole(String name, Role role);

    List<Task> findAllByStatus(Status status);
}