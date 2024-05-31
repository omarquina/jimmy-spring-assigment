package com.example.jimmy.rides.application.DeleteCar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.jimmy.rides.infraestructure.repositories.CarRepositoryJpa;

@Component
public class DeleteCar {

	@Autowired
	CarRepositoryJpa carRepository;

	public void execute(String carId) throws Exception {
		if (!this.carRepository.existsById(carId))
			throw new Exception("Car not exists!");

		carRepository.deleteById(carId);
	}
}
