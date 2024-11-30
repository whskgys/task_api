package com.nhnacademy.minidooray2teamtaskapi.model.task;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class TaskCreateCommand {
    private long taskId;
    private String name;
    private String description;
    private long projectId;
    private String milestone;

}
