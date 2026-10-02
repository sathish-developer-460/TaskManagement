package com.taskmanagementproject.service;

import java.util.List;

import com.taskmanagementproject.payload.TaskDto;

public interface TaskService {

	// save the task
	public TaskDto saveTask(long userid, TaskDto taskDto);

	public List<TaskDto> getAllTasks(long userid);

	public TaskDto getTask(long userid, long taskid);
	
	public void deleteTask(long userid, long todoid);
}