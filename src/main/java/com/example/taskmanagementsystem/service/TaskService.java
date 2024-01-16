package com.example.taskmanagementsystem.service;

import com.example.taskmanagementsystem.entity.Employee;
import com.example.taskmanagementsystem.entity.Task;
import com.example.taskmanagementsystem.enums.Role;
import com.example.taskmanagementsystem.enums.Status;
import com.example.taskmanagementsystem.repository.TaskRepository;
import com.example.taskmanagementsystem.util.TaskUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Page<Task> readAll(Pageable pageable) {
        Page<Task> allTasks = taskRepository.findAll(pageable);
        return allTasks;
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow();
    }

    public List<Task> findAllTasksByAuthorName(String name, Role role) {
        List<Task> tasks = taskRepository.findAllTasksByAuthorName(name, role);
        return tasks;
    }

    public List<Task> findAllTasksByExecutorName(String name, Role role) {
        List<Task> tasks = taskRepository.findAllTasksByExecutorName(name, role);
        return tasks;
    }

    public List<Task> findAllByStatus(Status status) {
        List<Task> tasks = taskRepository.findAllByStatus(status);
        return tasks;
    }

    @Transactional
    public void updateTaskExecutorById(Long taskId, Long newExecutorId) {
        Task task = getTaskById(taskId);
        Employee executorId = employeeService.getEmployeeById(newExecutorId);

        task.setExecutor(executorId);
        taskRepository.save(task);
    }

    @Transactional
    public void updateTaskStatus(Long taskId, Status status) {
        Task task = getTaskById(taskId);
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
        taskRepository.deleteById(id);
    }
}