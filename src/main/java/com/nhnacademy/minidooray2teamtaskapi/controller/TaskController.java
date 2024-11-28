package com.nhnacademy.minidooray2teamtaskapi.controller;

import com.nhnacademy.minidooray2teamtaskapi.model.task.Task;
import com.nhnacademy.minidooray2teamtaskapi.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("users/{userId}/projects/{projectId}")
@RestController
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/tasks")
    public ResponseEntity createTask(@RequestBody Task task) {
        taskService.create(task.getName(), task.getDescription());
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/tasks")
    public List<Task> getTasks(@PathVariable String userId, @PathVariable long projectId) {
        return taskService.findByProjectId(userId, projectId);
    }

    @GetMapping("/tasks/{taskId}")
    public Task getTask(@PathVariable String userId, @PathVariable long projectId, @PathVariable long taskId) {
        return taskService.findByTaskId(userId,projectId,taskId);
    }

    @PutMapping("/tasks/{taskId}")
    public ResponseEntity updateTask(@PathVariable String userId, @PathVariable long projectId, @PathVariable long taskId, @RequestBody Task task) {
        taskService.updateTask(userId,projectId,taskId,task);
        return new ResponseEntity(HttpStatus.OK);
    }


    @DeleteMapping("/tasks/{taskId}")
    public ResponseEntity deleteTask(@PathVariable String userId, @PathVariable long projectId, @PathVariable long taskId) {
        taskService.deleteTask(userId,projectId,taskId);
        return new ResponseEntity(HttpStatus.OK);
    }



}
