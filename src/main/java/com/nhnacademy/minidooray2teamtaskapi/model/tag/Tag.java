package com.nhnacademy.minidooray2teamtaskapi.model.tag;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nhnacademy.minidooray2teamtaskapi.model.task.Task;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private long tagId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "task_id")
    private Task task;

    @JsonProperty("name")
    private String name;

    public Tag(Task task, String name) {
        this.task = task;
        this.name = name;
    }
}
