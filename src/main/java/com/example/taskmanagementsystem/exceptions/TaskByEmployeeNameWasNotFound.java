package com.example.taskmanagementsystem.exceptions;

public class TaskByEmployeeNameWasNotFound extends RuntimeException{

    private String employeeName;

    public TaskByEmployeeNameWasNotFound(String employeeName) {
        super("The task with the employee name '" + employeeName + "' is not found.");
        this.employeeName = employeeName;
    }
}