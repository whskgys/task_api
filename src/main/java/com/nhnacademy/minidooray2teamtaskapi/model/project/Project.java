package com.nhnacademy.minidooray2teamtaskapi.model.project;

import com.nhnacademy.minidooray2teamtaskapi.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    @Setter
    private long projectId;

    private String admin;

    @Setter
    private String name;


    @ManyToMany
    @JoinTable(
            name = "project_user",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;

    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_state_id")
    private ProjectStateEntity projectState;

    public Project(String admin, String name, ProjectStateEntity projectState) {
        this.admin = admin;
        this.name = name;
        this.projectState = projectState;
        this.users = new ArrayList<>();
    }

    public void addUser(User user) {
        this.users.add(user);
    }
}
