package com.example.taskmanagementsystem.service;

import com.example.taskmanagementsystem.entity.Employee;
import com.example.taskmanagementsystem.entity.Task;
import com.example.taskmanagementsystem.enums.Role;
import com.example.taskmanagementsystem.enums.Status;
import com.example.taskmanagementsystem.exceptions.TaskByEmployeeNameWasNotFound;
import com.example.taskmanagementsystem.exceptions.TaskByStatusNotFound;
import com.example.taskmanagementsystem.exceptions.TaskNotFoundException;
import com.example.taskmanagementsystem.repository.TaskRepository;
import com.example.taskmanagementsystem.util.TaskUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final EmployeeService employeeService;

    public TaskService(TaskRepository taskRepository, EmployeeService employeeService) {
        this.taskRepository = taskRepository;
        this.employeeService = employeeService;
    }

    public Page<Task> findAllTasks(Pageable pageable) {
        Page<Task> allTasks = taskRepository.findAll(pageable);
        return allTasks;
    }

    public Task findTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
    }

    public List<Task> findAllTasksByNameAndRole(String name, Role role) {
        List<Task> tasks = taskRepository.findAllTasksByNameAndRole(name, role);
        if (tasks.isEmpty()) throw new TaskByEmployeeNameWasNotFound(name);
        return tasks;
    }

    public List<Task> findAllByStatus(Status status) {
        List<Task> tasks = taskRepository.findAllByStatus(status);
        if (tasks.isEmpty()) throw new TaskByStatusNotFound(status);
        return tasks;
    }

    @Transactional
    public void updateTaskExecutorById(Long taskId, Long newExecutorId) {
        Task task = findTaskById(taskId);
        Employee executorId = employeeService.getEmployeeById(newExecutorId);

        task.setExecutor(executorId);
        taskRepository.save(task);
    }

    @Transactional
    public void updateTaskStatus(Long taskId, Status status) {
        Task task = findTaskById(taskId);
        task.setStatus(status);
        taskRepository.save(task);
    }

    @Transactional
    public void create(Task task) {
        Task convertTask = TaskUtil.convert(task);
        taskRepository.save(convertTask);
    }

    @Transactional
    public void deleteById(Long id) {
        if (!taskRepository.existsById(id)) throw new TaskNotFoundException(id);
        taskRepository.deleteById(id);
    }
}