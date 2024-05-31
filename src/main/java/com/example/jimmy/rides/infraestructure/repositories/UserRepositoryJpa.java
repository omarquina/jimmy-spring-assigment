package com.example.jimmy.rides.infraestructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jimmy.rides.domain.Entity.User;

@Repository
public interface UserRepositoryJpa extends JpaRepository<User,Long>{}
