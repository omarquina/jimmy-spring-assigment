package com.example.jimmy.rides.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.jimmy.rides.domain.Entity.User;
import com.example.jimmy.rides.infraestructure.repositories.UserRepositoryJpa;

@Component
public class CreateUser {
	@Autowired
	private UserRepositoryJpa userRepository;


	public User execute(User user){
		return userRepository.save(user);
	}
}
