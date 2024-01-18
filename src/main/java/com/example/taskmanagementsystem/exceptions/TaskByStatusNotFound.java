package com.example.taskmanagementsystem.exceptions;

import com.example.taskmanagementsystem.enums.Status;

public class TaskByStatusNotFound extends RuntimeException {

    private Status status;

    public TaskByStatusNotFound(Status status) {
        super("The task with status '" + status + "' was not found. Perhaps such a task does not exist. Please check the correctness of the input");
        this.status = status;
    }
}
