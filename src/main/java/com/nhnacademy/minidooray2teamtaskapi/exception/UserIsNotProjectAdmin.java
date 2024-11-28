package com.nhnacademy.minidooray2teamtaskapi.exception;

public class UserIsNotProjectAdmin extends RuntimeException {
    public UserIsNotProjectAdmin(String message) {
        super(message);
    }
}
