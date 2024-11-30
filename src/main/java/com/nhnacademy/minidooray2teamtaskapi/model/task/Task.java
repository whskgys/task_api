package com.nhnacademy.minidooray2teamtaskapi.model.task;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.nhnacademy.minidooray2teamtaskapi.model.milestone.Milestone;
import com.nhnacademy.minidooray2teamtaskapi.model.project.Project;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;

    private String name;
    @JsonProperty("description")
    private String description;

    @ManyToOne(optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToOne
    @JoinColumn(name = "milestone",nullable = false)
    @JsonProperty("milestone")
    private Milestone milestone;

    @JsonProperty("createdDate")
    private LocalDateTime createdDate;

    public Task(String name, String description, Milestone milestone) {
        this.name = name;
        this.description = description;
        this.milestone = milestone;
        this.createdDate = LocalDateTime.now();
    }


}