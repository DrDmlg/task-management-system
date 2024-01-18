package com.example.taskmanagementsystem.exceptions;

public class TaskNotFoundException extends RuntimeException {

    private Long taskId;

    public TaskNotFoundException(Long taskId) {
        super("The task with ID '" + taskId + "' was not found. Perhaps such a task does not exist. Please check the correctness of the input");
        this.taskId = taskId;
    }
}