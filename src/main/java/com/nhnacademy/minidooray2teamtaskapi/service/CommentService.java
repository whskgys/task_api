package com.nhnacademy.minidooray2teamtaskapi.service;

import com.nhnacademy.minidooray2teamtaskapi.exception.TaskNotFoundException;
import com.nhnacademy.minidooray2teamtaskapi.exception.TaskNotInProject;
import com.nhnacademy.minidooray2teamtaskapi.model.comment.Comment;
import com.nhnacademy.minidooray2teamtaskapi.model.task.Task;
import com.nhnacademy.minidooray2teamtaskapi.repository.CommentRepository;
import com.nhnacademy.minidooray2teamtaskapi.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;
    @Autowired
    public CommentService(CommentRepository commentRepository, TaskRepository taskRepository) {
        this.commentRepository = commentRepository;
        this.taskRepository = taskRepository;
    }

    public List<Comment> getComments(long projectId,long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(TaskNotFoundException::new);
        if (task.getProject().getProjectId() != projectId) {
            throw new TaskNotInProject(task.getName() + "not in project");
        }
        return commentRepository.findAllByTask_TaskId(taskId);
    }

}
