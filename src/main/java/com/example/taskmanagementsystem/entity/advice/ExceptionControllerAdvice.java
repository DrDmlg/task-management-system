package com.example.taskmanagementsystem.entity.advice;

import com.example.taskmanagementsystem.exceptions.*;
import com.example.taskmanagementsystem.model.ErrorDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {

    private final ErrorDetails errorDetails = new ErrorDetails();

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ErrorDetails> exceptionTaskNotFoundHandler(TaskNotFoundException ex) {
        errorDetails.setMessage("Not found. Perhaps such the task does not exist");
        log.error(errorDetails.getMessage(), ex);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorDetails);
    }

    @ExceptionHandler(EmployeeListEmptyException.class)
    public ResponseEntity<ErrorDetails> exceptionEmployeeListEmptyHandler(EmployeeListEmptyException ex) {
        errorDetails.setMessage("The employee list is empty");
        log.error(errorDetails.getMessage(), ex);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorDetails);
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ErrorDetails> exceptionEmployeeNotFoundHandler(EmployeeNotFoundException ex) {
        errorDetails.setMessage("Employee not found");
        log.error(errorDetails.getMessage(), ex);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorDetails);
    }

    @ExceptionHandler(ErrorDeletingEmployee.class)
    public ResponseEntity<ErrorDetails> exceptionErrorDeletingEmployeeHandler(ErrorDeletingEmployee ex) {
        errorDetails.setMessage("You can delete your account from the service only if you have no unfinished tasks and you are not the author.");
        log.error(errorDetails.getMessage(), ex);
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(errorDetails);
    }

    @ExceptionHandler(CommentListEmptyException.class)
    public ResponseEntity<ErrorDetails> exceptionCommentListEmptyHandler(CommentListEmptyException ex) {
        errorDetails.setMessage("The comments list is empty");
        log.error(errorDetails.getMessage(), ex);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorDetails);
    }
}