package com.nhnacademy.minidooray2teamtaskapi.controller;

import com.nhnacademy.minidooray2teamtaskapi.model.tag.Tag;
import com.nhnacademy.minidooray2teamtaskapi.model.tag.TagCreateCommand;
import com.nhnacademy.minidooray2teamtaskapi.repository.TagRepository;
import com.nhnacademy.minidooray2teamtaskapi.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users/{userId}/projects/{projectId}/tasks/{taskId}")
public class TagController {

    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping("/tags")
    public ResponseEntity createTag(
            @PathVariable("userId") String userId,
            @PathVariable("projectId") long projectId,
            @PathVariable("taskId") long taskId,
            @RequestBody TagCreateCommand createCommand
    ) {
        tagService.enrollTag(userId, projectId, taskId, createCommand);
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/tags")
    public List<Tag> getTags(
            @PathVariable("taskId") long taskId
    ) {
        return tagService.getTags(taskId);
    }

    @DeleteMapping("/tags/{tagId}")
    public ResponseEntity deleteTag(
            @PathVariable("userId") String userId,
            @PathVariable("projectId") long projectId,
            @PathVariable("taskId") long taskId,
            @PathVariable("tagId") long tagId
    ) {
        tagService.deleteTage(userId, projectId, taskId, tagId);
        return ResponseEntity.status(200).build();
    }


}
