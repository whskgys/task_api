package com.nhnacademy.minidooray2teamtaskapi.repository;

import com.nhnacademy.minidooray2teamtaskapi.model.tag.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag,Long> {

    List<Tag> findAllByTask_TaskId(long taskId);

    Integer countByTask_TaskIdAndNameIs(Long task_taskId, String name);
}
