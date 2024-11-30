package com.nhnacademy.minidooray2teamtaskapi.service;

import com.nhnacademy.minidooray2teamtaskapi.exception.CommentNotFoundException;
import com.nhnacademy.minidooray2teamtaskapi.exception.TaskNotFoundException;
import com.nhnacademy.minidooray2teamtaskapi.exception.TaskNotInProject;
import com.nhnacademy.minidooray2teamtaskapi.exception.UserNotFoundException;
import com.nhnacademy.minidooray2teamtaskapi.model.comment.Comment;
import com.nhnacademy.minidooray2teamtaskapi.model.comment.CommentCreateCommand;
import com.nhnacademy.minidooray2teamtaskapi.model.task.Task;
import com.nhnacademy.minidooray2teamtaskapi.repository.CommentRepository;
import com.nhnacademy.minidooray2teamtaskapi.repository.TaskRepository;
import com.nhnacademy.minidooray2teamtaskapi.repository.UserRepository;
import com.nhnacademy.minidooray2teamtaskapi.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    @Autowired
    public CommentService(CommentRepository commentRepository, TaskRepository taskRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public List<Comment> getComments(long projectId,long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(TaskNotFoundException::new);
        if (task.getProject().getProjectId() != projectId) {
            throw new TaskNotInProject(task.getName() + "not in project");
        }
        return commentRepository.findAllByTask_TaskId(taskId);
    }

    public void enrollComment(long projectId, long taskId, CommentCreateCommand createCommand) {
        Task task = taskRepository.findById(taskId).orElseThrow(TaskNotFoundException::new);
        if (task.getProject().getProjectId() != projectId) {
            throw new TaskNotInProject(task.getName() + "not in project");
        }
        String userId = createCommand.getUserId();
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId + "not found"));

        commentRepository.save(new Comment(task, user, createCommand.getContent()));

    }

    public void updateComment(long projectId, long taskId, long commentId, CommentCreateCommand createCommand) {
        Task task = taskRepository.findById(taskId).orElseThrow(TaskNotFoundException::new);
        if (task.getProject().getProjectId() != projectId) {
            throw new TaskNotInProject(task.getName() + "not in project");
        }

        Comment findComment = commentRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException("comment not found"));

        findComment.setContent(createCommand.getContent());
        commentRepository.save(findComment);
    }

    public void removeComment(long projectId, long taskId, long commentId) {
        Task task = taskRepository.findById(taskId).orElseThrow(TaskNotFoundException::new);
        if (task.getProject().getProjectId() != projectId) {
            throw new TaskNotInProject(task.getName() + "not in project");
        }
        Comment findComment = commentRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException("comment not found"));
        commentRepository.delete(findComment);

    }



}
