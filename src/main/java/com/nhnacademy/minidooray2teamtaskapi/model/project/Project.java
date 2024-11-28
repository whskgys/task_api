package com.nhnacademy.minidooray2teamtaskapi.model.project;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private long projectId;

    private String admin;

    @Setter
    private String name;

    @Setter
    @Enumerated(EnumType.STRING)
    private ProjectState state;

}
