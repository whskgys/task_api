package com.nhnacademy.minidooray2teamtaskapi.repository;

import com.nhnacademy.minidooray2teamtaskapi.model.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAllByProjectId(long projectId);
}
