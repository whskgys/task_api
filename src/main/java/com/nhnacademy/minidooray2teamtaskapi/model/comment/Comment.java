package com.nhnacademy.minidooray2teamtaskapi.model.comment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nhnacademy.minidooray2teamtaskapi.model.task.Task;
import com.nhnacademy.minidooray2teamtaskapi.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
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
    @Setter
    private String content;

    @JsonProperty("createdDate")
    private LocalDateTime createdDate;

    public Comment(Task task, User user, String content) {
        this.task = task;
        this.user = user;
        this.content = content;
        createdDate = LocalDateTime.now();
    }
}
