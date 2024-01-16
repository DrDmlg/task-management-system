package com.example.taskmanagementsystem.controllers;

import com.example.taskmanagementsystem.entity.Comment;
import com.example.taskmanagementsystem.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comment")
@Tag(name = "Comment controller", description = "Endpoints for managing comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @Operation(summary = "Get all comments", description = "Retrieve a list of all comments.")
    @ApiResponse(responseCode = "200", description = "List of comments successfully retrieved.")
    @ApiResponse(responseCode = "404", description = "No comments found.")
    @GetMapping("/all")
    public ResponseEntity<List<Comment>> findAll() {
        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(commentService.findAll());
    }
}