package com.nhnacademy.minidooray2teamtaskapi.model.project;

import lombok.Getter;

@Getter
public class ProjectCreateCommand {
    private String admin;
    private String name;
    private String state;
}
