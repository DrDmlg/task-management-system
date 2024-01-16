package com.example.taskmanagementsystem.controllers;

import com.example.taskmanagementsystem.entity.Task;
import com.example.taskmanagementsystem.enums.Role;
import com.example.taskmanagementsystem.enums.Status;
import com.example.taskmanagementsystem.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Task controller", description = "Endpoints for managing tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @Operation(summary = "Get all tasks", description = "Retrieve a paginated list of all tasks.")
    @ApiResponse(responseCode = "200", description = "List of tasks successfully retrieved.")
    @ApiResponse(responseCode = "404", description = "No tasks found.")
    @GetMapping("/all")
    public ResponseEntity<Page<Task>> readAll(Pageable pageable) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(taskService.readAll(pageable));
    }

    @Operation(summary = "Get task by ID", description = "Retrieve a task by its ID.")
    @ApiResponse(responseCode = "200", description = "Task successfully retrieved.")
    @ApiResponse(responseCode = "404", description = "Task not found.")
    @GetMapping("/get/{id}")
    public ResponseEntity<Task> getTaskById(
            @Parameter(description = "Task ID") @PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(taskService.getTaskById(id));
    }

    @Operation(summary = "Find tasks by author", description = "Find tasks based on the author's name and role.")
    @ApiResponse(responseCode = "200", description = "List of tasks by author successfully retrieved.")
    @ApiResponse(responseCode = "404", description = "No tasks found.")
    @GetMapping("/author")
    public ResponseEntity<List<Task>> findAllTasksByAuthorName(
            @Parameter(description = "Author's name") @RequestParam String name,
            @Parameter(description = "Author's role") @RequestParam Role role) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(taskService.findAllTasksByAuthorName(name, role));
    }

    @Operation(summary = "Find tasks by executor", description = "Find tasks based on the executor's name and role.")
    @ApiResponse(responseCode = "200", description = "List of tasks by executor successfully retrieved.")
    @ApiResponse(responseCode = "404", description = "No tasks found.")
    @GetMapping("/executor")
    public ResponseEntity<List<Task>> findAllTasksByExecutorName(
            @Parameter(description = "Executor's name") @RequestParam String name,
            @Parameter(description = "Executor's role") @RequestParam Role role) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(taskService.findAllTasksByExecutorName(name, role));
    }

    @Operation(summary = "Find tasks by status", description = "Find tasks based on the task status.")
    @ApiResponse(responseCode = "200", description = "List of tasks by status successfully retrieved.")
    @ApiResponse(responseCode = "404", description = "No tasks found.")
    @GetMapping("/status")
    public ResponseEntity<List<Task>> findAllByStatus(
            @Parameter(description = "Task status") @RequestParam Status status) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(taskService.findAllByStatus(status));
    }

    @Operation(summary = "Update task executor", description = "Update the executor of a task.")
    @ApiResponse(responseCode = "200", description = "Task executor successfully updated.")
    @PutMapping("/change/executor")
    public HttpStatus updateTaskEntityByExecutorId(
            @Parameter(description = "Task ID") @RequestParam Long taskId,
            @Parameter(description = "New executor ID") @RequestParam Long newExecutorId) {
        taskService.updateTaskExecutorById(taskId, newExecutorId);
        return HttpStatus.OK;
    }

    @Operation(summary = "Update task status", description = "Update the status of a task.")
    @ApiResponse(responseCode = "200", description = "Task status successfully updated.")
    @PatchMapping("/change/status")
    public HttpStatus updateTaskStatus(
            @Parameter(description = "Task ID") @RequestParam Long taskId,
            @Parameter(description = "New task status") @RequestParam Status status) {
        taskService.updateTaskStatus(taskId, status);
        return HttpStatus.OK;
    }

    @Operation(summary = "Create task", description = "Create a new task.")
    @ApiResponse(responseCode = "200", description = "Task successfully created.")
    @PostMapping("/add")
    public HttpStatus create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Task details") @RequestBody Task task) {
        taskService.create(task);
        return HttpStatus.OK;
    }

    @Operation(summary = "Delete task by ID", description = "Delete a task by its ID.")
    @ApiResponse(responseCode = "200", description = "Task successfully deleted.")
    @ApiResponse(responseCode = "404", description = "Task not found.")
    @DeleteMapping("delete/{id}")
    public HttpStatus deleteById(
            @Parameter(description = "Task ID") @PathVariable Long id) {
        taskService.deleteById(id);
        return HttpStatus.OK;
    }
}