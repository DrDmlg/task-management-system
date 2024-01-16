package com.example.taskmanagementsystem.controllers;

import com.example.taskmanagementsystem.entity.Task;
import com.example.taskmanagementsystem.enums.Role;
import com.example.taskmanagementsystem.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/task")

public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/all")
    public ResponseEntity<Page<Task>> readAll(Pageable pageable) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(taskService.readAll(pageable));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(taskService.getTaskById(id));
    }

    @GetMapping("/author")
    public ResponseEntity<List<Task>> findAllTasksByAuthorName(
            @RequestParam String name,
            @RequestParam Role role) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(taskService.findAllTasksByAuthorName(name, role));
    }
}