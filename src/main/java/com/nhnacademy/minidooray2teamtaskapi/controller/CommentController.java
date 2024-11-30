package com.nhnacademy.minidooray2teamtaskapi.controller;

import com.nhnacademy.minidooray2teamtaskapi.model.comment.Comment;
import com.nhnacademy.minidooray2teamtaskapi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects/{projectId}/tasks/{taskId}")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/comments")
    public List<Comment> getCommands(
            @PathVariable("projectId") long projectId,
            @PathVariable("taskId") long taskId
    ) {
        return commentService.getComments(projectId, taskId);
    }

//    @PostMapping("/")
 }
