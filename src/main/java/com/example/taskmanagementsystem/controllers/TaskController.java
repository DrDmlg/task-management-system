package com.example.taskmanagementsystem.controllers;

import com.example.taskmanagementsystem.entity.Task;
import com.example.taskmanagementsystem.enums.Role;
import com.example.taskmanagementsystem.enums.Status;
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

    @GetMapping("/executor")
    public ResponseEntity<List<Task>> findAllTasksByExecutorName(
            @RequestParam String name,
            @RequestParam Role role) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(taskService.findAllTasksByExecutorName(name, role));
    }

    @GetMapping("/status")
    public ResponseEntity<List<Task>> findAllByStatus(
            @RequestParam Status status) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(taskService.findAllByStatus(status));
    }

    @PutMapping("/change/executor")
    public HttpStatus updateTaskEntityByExecutorId(
            @RequestParam Long taskId,
            @RequestParam Long newExecutorId) {
        taskService.updateTaskExecutorById(taskId, newExecutorId);
        return HttpStatus.OK;
    }

    public HttpStatus updateTaskStatus(
            @RequestParam Long taskId,
            @RequestParam Status status) {
        taskService.updateTaskStatus(taskId, status);
        return HttpStatus.OK;
    }
}