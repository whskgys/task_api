package com.nhnacademy.minidooray2teamtaskapi.service;

import com.nhnacademy.minidooray2teamtaskapi.model.task.Task;
import com.nhnacademy.minidooray2teamtaskapi.repository.TaskRepository;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@NoArgsConstructor
public class TaskService {

    private TaskRepository taskRepository;

    public Task create(String name, String description) {
        return taskRepository.save(new Task(name, description));
    }

    public List<Task> findByProjectId(String userId, long projectId) {
        return taskRepository.findAllByProjectId(projectId);
    }

    public Task findByTaskId(String userId, long projectId, long taskId) {
        return taskRepository.findById(taskId).orElseThrow(()-> new RuntimeException("Task not found"));
    }

    public Task updateTask(String userId, long projectId, long taskId, Task task) {
        Task taskToUpdate =  taskRepository.findById(taskId).orElseThrow(()-> new RuntimeException("Task not found"));
        taskToUpdate.setName(task.getName());
        taskToUpdate.setDescription(task.getDescription());
        return taskRepository.save(taskToUpdate);
    }

    public void deleteTask(String userId, long projectId, long taskId) {
        taskRepository.deleteById(taskId);
    }





}
