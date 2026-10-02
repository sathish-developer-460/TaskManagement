package com.taskmanagementproject.service;

import com.taskmanagementproject.payload.UserDto;

public interface UserService {
	UserDto createUser(UserDto userDto);
}