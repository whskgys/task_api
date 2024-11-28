package com.nhnacademy.minidooray2teamtaskapi.service;

import com.nhnacademy.minidooray2teamtaskapi.exception.ProjectNotFoundException;
import com.nhnacademy.minidooray2teamtaskapi.exception.UserIsNotProjectAdmin;
import com.nhnacademy.minidooray2teamtaskapi.exception.UserNotBelongToProject;
import com.nhnacademy.minidooray2teamtaskapi.model.project.Project;
import com.nhnacademy.minidooray2teamtaskapi.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project getProject(String userId, String projectId) {
        if (projectRepository.isUserInProject(userId, Long.parseLong(projectId)) == 0) {
            throw new UserNotBelongToProject(userId + "not belong to project");
        }

        return projectRepository.findById(Long.parseLong(projectId)).orElseThrow(ProjectNotFoundException::new);
    }

    public List<Project> getProjectAll(String userId) {
        return projectRepository.findAllBy(userId);
    }

    public void enrollProject(String userId, Project project) {
        projectRepository.save(project);
        projectRepository.saveProjectUser(userId,project.getProjectId());
    }

    public void removeProject(String userId, String projectId) {
        Project findProject = projectRepository.findById(Long.parseLong(projectId)).orElseThrow(ProjectNotFoundException::new);
        if (!findProject.getAdmin().equalsIgnoreCase(userId)) {
            throw new UserIsNotProjectAdmin(userId + " is not project admin");
        }
        projectRepository.delete(findProject);

    }

    public void updateProject(String userId, String projectId, Project project) {
        Project findProject = projectRepository.findById(Long.parseLong(projectId)).orElseThrow(ProjectNotFoundException::new);
        if (!project.getAdmin().equalsIgnoreCase(userId)) {
            throw new UserIsNotProjectAdmin(userId + " is not project admin");
        }
        findProject.setName(project.getName());
        findProject.setState(project.getState());
        projectRepository.save(findProject);
    }
}
