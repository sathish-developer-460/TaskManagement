package com.taskmanagementproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskmanagementproject.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

	List<Task> findAllByUsersId(long userid);
}