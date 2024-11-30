package com.nhnacademy.minidooray2teamtaskapi.controller;

import com.nhnacademy.minidooray2teamtaskapi.exception.ProjectNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(ProjectNotFoundException.class)
    @ResponseBody
    public ResponseEntity notfound(Exception e) {
        return ResponseEntity.status(404).build();
    }
}
