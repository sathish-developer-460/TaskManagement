package com.taskmanagementproject.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.taskmanagementproject.entity.Users;
import com.taskmanagementproject.exception.UserNotFound;
import com.taskmanagementproject.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Users user = userRepository.findByEmail(email).orElseThrow(
				() -> new UserNotFound(String.format("User with email : %s is not found", email)));

		Set<String> roles = new HashSet<>();
		roles.add("ROLE_ADMIN");

		return new org.springframework.security.core.userdetails.User(
				user.getEmail(), user.getPassword(), userAuthorities(roles));
	}

	private Collection<? extends GrantedAuthority> userAuthorities(Set<String> roles) {
		return roles.stream()
				.map(role -> new SimpleGrantedAuthority(role))
				.collect(Collectors.toList());
	}
}