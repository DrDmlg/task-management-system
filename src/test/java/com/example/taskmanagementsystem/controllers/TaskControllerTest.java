package com.example.taskmanagementsystem.controllers;

import com.example.taskmanagementsystem.entity.Task;
import com.example.taskmanagementsystem.enums.Role;
import com.example.taskmanagementsystem.service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskControllerTest {

    @InjectMocks
    private TaskController taskController;

    @Mock
    private TaskService taskService;

    @Test
    void testReadAll_ShouldReturnListOfTasks() {

        Page<Task> mockTaskPage = new PageImpl<>(List.of(new Task()));

        when(taskService.findAllTasks(any(Pageable.class))).thenReturn(mockTaskPage);

        ResponseEntity<Page<Task>> responseEntity = taskController.findAllTasks(Pageable.unpaged());

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockTaskPage, responseEntity.getBody());
    }

    @Test
    void testGetTaskById_ShouldReturnInfoBySpecificTask() {
        Task task = new Task();

        Long specificId = 1L;

        when(taskService.findTaskById(any(Long.class))).thenReturn(task);

        ResponseEntity<Task> response = taskController.findTaskById(specificId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(task, response.getBody());
    }

    @Test
    void testFindAllTasksByAuthorName_ShouldReturnInfoAboutTasksThatBelongToASpecificAuthor() {
        String authorName = "TestAuthor";
        Role role = Role.AUTHOR;

        List<Task> mockTaskList = List.of(new Task());

        when(taskService.findAllTasksByAuthorName(any(String.class), any(Role.class))).thenReturn(mockTaskList);

        ResponseEntity<List<Task>> responseEntity = taskController.findAllTasksByAuthorName(authorName, role);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockTaskList, responseEntity.getBody());
    }

    @Test
    void testFindAllTasksByExecutorName_ShouldReturnInfoAboutTasksThatBelongToASpecificExecutor() {
        String authorName = "TestAuthor";
        Role role = Role.AUTHOR;

        List<Task> mockTaskList = List.of(new Task());

        when(taskService.findAllTasksByAuthorName(any(String.class), any(Role.class))).thenReturn(mockTaskList);

        ResponseEntity<List<Task>> responseEntity = taskController.findAllTasksByAuthorName(authorName, role);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockTaskList, responseEntity.getBody());
    }
}