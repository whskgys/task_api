package com.nhnacademy.minidooray2teamtaskapi.exception;

public class UserNotBelongToProject extends RuntimeException {
    public UserNotBelongToProject(String message) {
        super(message);
    }
}
