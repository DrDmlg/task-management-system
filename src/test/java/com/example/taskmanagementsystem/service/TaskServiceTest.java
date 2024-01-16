package com.example.taskmanagementsystem.service;

import com.example.taskmanagementsystem.entity.Employee;
import com.example.taskmanagementsystem.entity.Task;
import com.example.taskmanagementsystem.enums.Role;
import com.example.taskmanagementsystem.enums.Status;
import com.example.taskmanagementsystem.exceptions.TaskNotFoundException;
import com.example.taskmanagementsystem.exceptions.TasksListEmptyException;
import com.example.taskmanagementsystem.repository.TaskRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private TaskService taskService;

    @Test
    void testReadAll_ShouldReturnListWithOneElement() {
        Task task = new Task();

        Page<Task> allTasks = new PageImpl<>(List.of(task));

        when(taskRepository.findAll(any(Pageable.class))).thenReturn(allTasks);

        Page<Task> resultPage = taskService.readAll(Pageable.unpaged());

        assertEquals(1, resultPage.getSize());
    }

    @Test
    void testReadAll_ShouldThrowException_WhenListIsNull() {

        when(taskRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(Collections.emptyList()));

        assertThrows(TasksListEmptyException.class, () -> taskService.readAll(Pageable.unpaged()));
    }

    @Test
    void testGetTaskById_ShouldReturnTask_WhenInsertSpecificTaskId() {
        Task task = new Task();

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        Task result = taskService.getTaskById(1L);

        assertEquals(task, result);

        verify(taskRepository, times(1)).findById(1L);
    }

    @Test
    void testGetTaskById_ShouldThrowException_WhenSpecificTaskNotFound() {

        when(taskRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(TaskNotFoundException.class, () -> taskService.getTaskById(1L));

        verify(taskRepository, times(1)).findById(1L);
    }

    @Test
    void testFindAllTasksByAuthorName_ShouldReturnTasksList() {
        String name = "John Doe";
        List<Task> mockTasks = Collections.singletonList(new Task());

        when(taskRepository.findAllTasksByAuthorName(name, Role.AUTHOR)).thenReturn(mockTasks);

        List<Task> result = taskService.findAllTasksByAuthorName(name, Role.AUTHOR);

        assertEquals(mockTasks, result);
        verify(taskRepository, times(1)).findAllTasksByAuthorName(name, Role.AUTHOR);
    }

    @Test
    void testFindAllTasksByAuthorName_ShouldThrowException_WhenTasksNotFound() {
        String name = "John Doe";

        when(taskRepository.findAllTasksByAuthorName(name, Role.AUTHOR)).thenReturn(Collections.emptyList());

        assertThrows(TaskNotFoundException.class, () -> taskService.findAllTasksByAuthorName(name, Role.AUTHOR));

        verify(taskRepository, times(1)).findAllTasksByAuthorName(name, Role.AUTHOR);
    }

    @Disabled
    @Test
    void testUpdateTaskExecutor() {
        Task task = new Task();
        Employee executor = new Employee();

        when(taskService.getTaskById(anyLong())).thenReturn(task);
        when(employeeService.getEmployeeById(anyLong())).thenReturn(executor);

        taskService.updateTaskExecutorById(1L, 2L);

        verify(taskService, times(1)).getTaskById(1L);
        verify(employeeService, times(1)).getEmployeeById(2L);
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    void testDeleteById() {
        Long taskId = 1L;

        when(taskRepository.existsById(taskId)).thenReturn(true);

        assertDoesNotThrow(() -> taskService.deleteById(taskId));

        verify(taskRepository, times(1)).existsById(taskId);
        verify(taskRepository, times(1)).deleteById(taskId);
    }

    @Test
    void testDeleteById_ShouldThrowException_WhenSpecificTaskNotFound() {
        Long taskId = 1L;

        when(taskRepository.existsById(taskId)).thenReturn(false);

        assertThrows(TaskNotFoundException.class, () -> taskService.deleteById(taskId));

        verify(taskRepository, times(1)).existsById(taskId);
        verify(taskRepository, never()).deleteById(anyLong());
    }

    @Test
    void testUpdateTaskStatus() {
        Long taskId = 1L;
        Status newStatus = Status.COMPLETED;
        Task mockTask = new Task();
        mockTask.setId(taskId);
        mockTask.setStatus(Status.IN_PROGRESS);

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(mockTask));

        taskService.updateTaskStatus(taskId, newStatus);

        assertEquals(newStatus, mockTask.getStatus());
        verify(taskRepository, times(1)).save(mockTask);
    }
}