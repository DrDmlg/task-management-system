package com.example.taskmanagementsystem.util;

import com.example.taskmanagementsystem.entity.Task;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TaskUtil {

    public static Task convert(Task task) {
        Task newTask = new Task();
        newTask.setId(task.getId());
        newTask.setTitle(task.getTitle());
        newTask.setDescription(task.getDescription());
        newTask.setStatus(task.getStatus());
        newTask.setPriority(task.getPriority());
        newTask.setAuthor(task.getAuthor());
        newTask.setExecutor(task.getExecutor());
        newTask.setComment(task.getComment());
        return newTask;
    }
}
