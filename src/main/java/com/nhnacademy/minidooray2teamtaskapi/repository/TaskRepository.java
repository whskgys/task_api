package com.nhnacademy.minidooray2teamtaskapi.repository;

import com.nhnacademy.minidooray2teamtaskapi.model.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query(value="SELECT * FROM task WHERE project_id = :projectId", nativeQuery = true)
    List<Task> findAllByProjectId(long projectId);
}
