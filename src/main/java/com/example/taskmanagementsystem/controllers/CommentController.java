package com.example.taskmanagementsystem.controllers;

import com.example.taskmanagementsystem.entity.Comment;
import com.example.taskmanagementsystem.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Comment>> readAll() {
        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(commentService.readAll());
    }
}
