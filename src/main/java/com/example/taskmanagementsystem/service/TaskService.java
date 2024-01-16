package com.example.taskmanagementsystem.service;

import com.example.taskmanagementsystem.entity.Task;
import com.example.taskmanagementsystem.repository.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
}