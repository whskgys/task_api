package com.nhnacademy.minidooray2teamtaskapi.exception;

public class TaskNotInProject extends RuntimeException {
    public TaskNotInProject(String message) {
        super(message);
    }
}
