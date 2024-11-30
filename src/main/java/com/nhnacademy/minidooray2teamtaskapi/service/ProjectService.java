package com.nhnacademy.minidooray2teamtaskapi.service;

import com.nhnacademy.minidooray2teamtaskapi.exception.ProjectNotFoundException;
import com.nhnacademy.minidooray2teamtaskapi.exception.UserIsNotProjectAdmin;
import com.nhnacademy.minidooray2teamtaskapi.exception.UserNotBelongToProject;
import com.nhnacademy.minidooray2teamtaskapi.model.project.Project;
import com.nhnacademy.minidooray2teamtaskapi.model.project.ProjectCreateCommand;
import com.nhnacademy.minidooray2teamtaskapi.model.project.ProjectState;
import com.nhnacademy.minidooray2teamtaskapi.model.project.ProjectStateEntity;
import com.nhnacademy.minidooray2teamtaskapi.repository.ProjectRepository;
import com.nhnacademy.minidooray2teamtaskapi.repository.UserRepository;
import com.nhnacademy.minidooray2teamtaskapi.user.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }
    @Transactional
    public Project getProject(String userId, String projectId) {
        if (projectRepository.isUserInProject(userId, Long.parseLong(projectId)) == 0) {
            throw new UserNotBelongToProject(userId + "not belong to project");
        }

        return projectRepository.findById(Long.parseLong(projectId)).orElseThrow(ProjectNotFoundException::new);
    }
    @Transactional
    public List<Project> getProjectAll(String userId) {
        return projectRepository.findByUsers_Id(userId);
    }

    public void enrollProject(String userId, ProjectCreateCommand createCommand) {
        ProjectState projectState = ProjectState.valueOf(createCommand.getState().toUpperCase());
        ProjectStateEntity projectStateEntity = new ProjectStateEntity(
                projectState.getId(),
                projectState.name()
        );


        Project project = new Project(
                createCommand.getAdmin(),
                createCommand.getName(),
                projectStateEntity
        );


        //유저 없으면 생성
        Optional<User> existingUser = userRepository.findById(userId);
        User user = existingUser.orElseGet(() -> userRepository.save(new User(userId)));

        project.addUser(user);

        projectRepository.save(project);

    }

    public void removeProject(String userId, String projectId) {
        Project findProject = projectRepository.findById(Long.parseLong(projectId)).orElseThrow(ProjectNotFoundException::new);
        if (!findProject.getAdmin().equalsIgnoreCase(userId)) {
            throw new UserIsNotProjectAdmin(userId + " is not project admin");
        }
        projectRepository.delete(findProject);

    }

    public void updateProject(String userId, String projectId, ProjectCreateCommand createCommand) {
        Project findProject = projectRepository.findById(Long.parseLong(projectId)).orElseThrow(ProjectNotFoundException::new);
        if (!createCommand.getAdmin().equalsIgnoreCase(userId)) {
            throw new UserIsNotProjectAdmin(userId + " is not project admin");
        }
        if (Objects.nonNull(createCommand.getState())) {
            ProjectState projectState = ProjectState.valueOf(createCommand.getState().toUpperCase());
            ProjectStateEntity projectStateEntity = new ProjectStateEntity(
                    projectState.getId(),
                    projectState.name()
            );
            findProject.setProjectState(projectStateEntity);

        }

        if (Objects.nonNull(createCommand.getName())) {
            findProject.setName(createCommand.getName());
        }

//        findProject.setState(project.getState());
        projectRepository.save(findProject);
    }
}
