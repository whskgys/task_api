package com.nhnacademy.minidooray2teamtaskapi.controller;

import com.nhnacademy.minidooray2teamtaskapi.model.comment.Comment;
import com.nhnacademy.minidooray2teamtaskapi.model.comment.CommentCreateCommand;
import com.nhnacademy.minidooray2teamtaskapi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users/{userId}/projects/{projectId}/tasks/{taskId}")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/comments")
    public List<Comment> getCommands(
            @PathVariable("userId") String userId,
            @PathVariable("projectId") long projectId,
            @PathVariable("taskId") long taskId
    ) {
        return commentService.getComments(projectId, taskId);
    }

    @PostMapping("/comments")
    public ResponseEntity createComment(
            @PathVariable("projectId") long projectId,
            @PathVariable("taskId") long taskId,
            @RequestBody CommentCreateCommand createCommand
    ) {
        commentService.enrollComment(projectId, taskId, createCommand);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/comments/{commentId}")
    public ResponseEntity updateComment(
            @PathVariable("userId") String userId,
            @PathVariable("projectId") long projectId,
            @PathVariable("taskId") long taskId,
            @PathVariable("commentId") long commentId,
            @RequestBody CommentCreateCommand createCommand
    ) {
        commentService.updateComment(userId, projectId, taskId, commentId, createCommand);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity deleteComment(
            @PathVariable("userId") String userId,
            @PathVariable("projectId") long projectId,
            @PathVariable("taskId") long taskId,
            @PathVariable("commentId") long commentId
    ) {
        commentService.removeComment(userId,projectId, taskId, commentId);
        return ResponseEntity.status(200).build();
    }
}
