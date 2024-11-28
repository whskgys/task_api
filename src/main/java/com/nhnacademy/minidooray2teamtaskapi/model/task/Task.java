package com.nhnacademy.minidooray2teamtaskapi.model.task;

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
    private Long task_id;

    private String name;

    private String description;
//
//    @ManyToOne
//    private Project project;
//
//    @ManyToOne
//    private Milestone milestone;

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
    }


}
