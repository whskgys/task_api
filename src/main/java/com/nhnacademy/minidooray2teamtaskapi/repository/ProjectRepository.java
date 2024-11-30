package com.nhnacademy.minidooray2teamtaskapi.repository;

import com.nhnacademy.minidooray2teamtaskapi.model.project.Project;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
public interface ProjectRepository extends JpaRepository<Project,Long> {

//    @Query(value = "select (project_id,state,name,admin) from project_user pu left join project p on  pu.project_id = p.project_id where pu.user_id = :userId",nativeQuery = true)
//    List<Project> findAllBy(@Param("userId") String userId);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO project_user (project_id, user_id) VALUES (:projectId, :userId)", nativeQuery = true)
    void saveProjectUser(@Param("userId") String userId, @Param("projectId") Long project_id);

    @Query(value = "SELECT EXISTS (SELECT 1 FROM project_user WHERE user_id = :userId AND project_id = :projectId)", nativeQuery = true)
    Long isUserInProject(@Param("userId") String userId, @Param("projectId") Long project_id);

    List<Project> findByUsers_Id(String userId);

}
