package com.example.taskmanagementsystem.util;

import com.example.taskmanagementsystem.entity.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskUtil {

    public static Task convert(Task task) {
        return Task.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .priority(task.getPriority())
                .author(task.getAuthor())
                .executor(task.getExecutor())
                .comment(task.getComment())
                .build();
    }
}