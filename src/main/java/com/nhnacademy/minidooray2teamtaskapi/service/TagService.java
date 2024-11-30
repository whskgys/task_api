package com.nhnacademy.minidooray2teamtaskapi.service;

import com.nhnacademy.minidooray2teamtaskapi.exception.*;
import com.nhnacademy.minidooray2teamtaskapi.model.project.Project;
import com.nhnacademy.minidooray2teamtaskapi.model.tag.Tag;
import com.nhnacademy.minidooray2teamtaskapi.model.tag.TagCreateCommand;
import com.nhnacademy.minidooray2teamtaskapi.model.task.Task;
import com.nhnacademy.minidooray2teamtaskapi.repository.ProjectRepository;
import com.nhnacademy.minidooray2teamtaskapi.repository.TagRepository;
import com.nhnacademy.minidooray2teamtaskapi.repository.TaskRepository;
import com.nhnacademy.minidooray2teamtaskapi.repository.UserRepository;
import com.nhnacademy.minidooray2teamtaskapi.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    private final TagRepository tagRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public TagService(TagRepository tagRepository, ProjectRepository projectRepository, UserRepository userRepository, TaskRepository taskRepository) {
        this.tagRepository = tagRepository;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    public void enrollTag(String userId, long projectId, long taskId, TagCreateCommand createCommand) {
        if (1 <= tagRepository.countByTask_TaskIdAndNameIs(taskId, createCommand.getName())) {
            return;
        }
        Project project = projectRepository.findById(projectId).orElseThrow(ProjectNotFoundException::new);
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId + " can not found"));
        if (!project.getUsers().contains(user)) {
            throw new UserNotBelongToProject(userId + " not belong in " + project.getName());
        }
        Task task = taskRepository.findById(taskId).orElseThrow(TaskNotFoundException::new);
        tagRepository.save(new Tag(task, createCommand.getName()));
    }

    public List<Tag> getTags(long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(TaskNotFoundException::new);
        return tagRepository.findAllByTask_TaskId(taskId);
    }

    public void deleteTage(String userId, long projectId, long taskId, long tagId) {
        Project project = projectRepository.findById(projectId).orElseThrow(ProjectNotFoundException::new);
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId + " can not found"));
        List<User> users = project.getUsers();
        if (!users.contains(user)) {
            throw new UserNotBelongToProject(userId + " not belong in " + project.getName());
        }
        Tag tag = tagRepository.findById(tagId).orElseThrow(() -> new TagNotFoundException(" tag not found"));
        tagRepository.delete(tag);

    }

}
