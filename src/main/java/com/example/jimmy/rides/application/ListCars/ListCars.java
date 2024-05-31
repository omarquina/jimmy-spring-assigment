package com.example.jimmy.rides.application.ListCars;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.jimmy.rides.domain.Entity.Car;
import com.example.jimmy.rides.infraestructure.repositories.CarRepositoryJpa;

@Component
public class ListCars {

  @Autowired
	CarRepositoryJpa carRepository;

	public List<Car> execute() {
		return this.carRepository.findAll();
	}
}
