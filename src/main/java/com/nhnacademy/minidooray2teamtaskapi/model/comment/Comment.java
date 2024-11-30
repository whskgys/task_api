package com.nhnacademy.minidooray2teamtaskapi.model.comment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nhnacademy.minidooray2teamtaskapi.model.task.Task;
import com.nhnacademy.minidooray2teamtaskapi.user.User;
import jakarta.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    @JsonProperty("id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false) // comments 테이블의 task_id를 Task와 매핑
    private Task task;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // comments 테이블의 task_id를 Task와 매핑
    @JsonProperty("user")
    private User user;

    @JsonProperty("content")
    private String content;
}
