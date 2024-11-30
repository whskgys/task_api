package com.nhnacademy.minidooray2teamtaskapi.model.task;


import com.nhnacademy.minidooray2teamtaskapi.model.milestone.Milestone;
import com.nhnacademy.minidooray2teamtaskapi.model.milestone.MilestoneState;
import com.nhnacademy.minidooray2teamtaskapi.model.project.Project;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;

    private String name;

    private String description;


    @ManyToOne(optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToOne
    @JoinColumn(name = "milestone",nullable = false)
    private Milestone milestone;

    public Task(String name, String description, Milestone milestone) {
        this.name = name;
        this.description = description;
        this.milestone = milestone;

    }


}