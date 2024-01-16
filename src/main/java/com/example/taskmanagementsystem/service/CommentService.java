package com.example.taskmanagementsystem.service;

import com.example.taskmanagementsystem.entity.Comment;
import com.example.taskmanagementsystem.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> readAll() {
        List<Comment> allComment = commentRepository.findAll();
        return allComment;
    }
}
