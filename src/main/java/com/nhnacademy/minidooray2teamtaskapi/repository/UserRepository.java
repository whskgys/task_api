package com.nhnacademy.minidooray2teamtaskapi.repository;

import com.nhnacademy.minidooray2teamtaskapi.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
}
