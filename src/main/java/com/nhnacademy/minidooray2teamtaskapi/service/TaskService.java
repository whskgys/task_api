package com.nhnacademy.minidooray2teamtaskapi.service;

import com.nhnacademy.minidooray2teamtaskapi.exception.ProjectNotFoundException;
import com.nhnacademy.minidooray2teamtaskapi.model.milestone.Milestone;
import com.nhnacademy.minidooray2teamtaskapi.model.milestone.MilestoneState;
import com.nhnacademy.minidooray2teamtaskapi.model.project.Project;
import com.nhnacademy.minidooray2teamtaskapi.model.task.Task;
import com.nhnacademy.minidooray2teamtaskapi.model.task.TaskCreateCommand;
import com.nhnacademy.minidooray2teamtaskapi.repository.MilestoneRepository;
import com.nhnacademy.minidooray2teamtaskapi.repository.ProjectRepository;
import com.nhnacademy.minidooray2teamtaskapi.repository.TaskRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ProjectRepository projectRepository;

    public Task create(long projectId, TaskCreateCommand taskCreateCommand) {
        MilestoneState milestonestate = MilestoneState.WAITING;
        Milestone milestone = new Milestone(
                (long) milestonestate.getId(),
                milestonestate.name()
        );
        Task task = new Task(taskCreateCommand.getName(), taskCreateCommand.getDescription(), milestone);

        Project findProject = projectRepository.findById(projectId).orElseThrow(ProjectNotFoundException::new);
        task.setProject(findProject);
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
