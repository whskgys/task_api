package com.nhnacademy.minidooray2teamtaskapi.service;

import com.nhnacademy.minidooray2teamtaskapi.model.project.Project;
import com.nhnacademy.minidooray2teamtaskapi.model.task.Task;
import com.nhnacademy.minidooray2teamtaskapi.model.task.TaskCreateCommand;
import com.nhnacademy.minidooray2teamtaskapi.repository.ProjectRepository;
import com.nhnacademy.minidooray2teamtaskapi.repository.TaskRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@NoArgsConstructor
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ProjectRepository projectRepository;

    public Task create(TaskCreateCommand taskCreateCommand) {
        Task task = new Task(taskCreateCommand.getName(), taskCreateCommand.getDescription());
        task.setProject(projectRepository.findById(taskCreateCommand.getProjectId()).orElse(null));

        return taskRepository.save(task);
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
