package com.nhnacademy.minidooray2teamtaskapi.model.comment;

import lombok.Getter;

@Getter
public class CommentCreateCommand {
    private String userId;
    private String content;
}
