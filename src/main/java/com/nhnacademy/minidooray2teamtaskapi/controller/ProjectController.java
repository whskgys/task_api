package com.nhnacademy.minidooray2teamtaskapi.controller;

import com.nhnacademy.minidooray2teamtaskapi.model.project.Project;
import com.nhnacademy.minidooray2teamtaskapi.model.project.ProjectCreateCommand;
import com.nhnacademy.minidooray2teamtaskapi.model.project.ProjectState;
import com.nhnacademy.minidooray2teamtaskapi.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectController {
    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("users/{userId}/projects")
    public ResponseEntity createProject(@PathVariable(name = "userId") String userId, @RequestBody ProjectCreateCommand createCommand) {
        projectService.enrollProject(userId, createCommand);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("users/{userId}/projects/{projectId}")
    public Project getProject(@PathVariable(name = "userId") String userId, @PathVariable(name = "projectId") String projectId) {
        return projectService.getProject(userId, projectId);
    }

    @GetMapping("users/{userId}/projects")
    public List<Project> getProjects(@PathVariable(name = "userId") String userId) {
        return projectService.getProjectAll(userId);
    }


    @DeleteMapping("users/{userId}/projects/{projectId}")
    public ResponseEntity deleteProject(@PathVariable(name = "userId") String userId, @PathVariable(name = "projectId") String projectId) {
        projectService.removeProject(userId, projectId);
        return ResponseEntity.status(200).build();

    }

    @PostMapping("users/{userId}/projects/{projectId}")
    public ResponseEntity putProject(@PathVariable(name = "userId") String userId, @PathVariable(name = "projectId") String projectId, @RequestBody ProjectCreateCommand createCommand) {
        projectService.updateProject(userId, projectId, createCommand);
        return ResponseEntity.status(200).build();
    }
}
