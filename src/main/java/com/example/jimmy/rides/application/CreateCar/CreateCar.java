package com.example.jimmy.rides.application.CreateCar;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.jimmy.rides.application.CreateCar.dto.CreateCarDto;
import com.example.jimmy.rides.domain.Entity.Car;
import com.example.jimmy.rides.infraestructure.repositories.CarRepositoryJpa;

@Component
public class CreateCar {
	@Autowired
	private CarRepositoryJpa carRepository;
	private Car newCar;

	public Car execute(CreateCarDto carDto) throws Exception {
		try {
		this.preprocessData(carDto);
		this.validate();
		this.save();
		return this.newCar;
		
		} catch(Exception error) {
			System.out.println(error.getMessage());
			throw error;
		}
	}

	private void validate() throws Exception {
		if (this.newCar.getMake() == "" || this.newCar.getModel() == "" ){
			throw new Exception("Make and Model must not be empty");
		}
	}

	private void preprocessData(CreateCarDto dto){
	  this.newCar = new Car();
		newCar.setId(this.generateId());
		newCar.setMake(dto.getMake());
		newCar.setModel(dto.getModel());

	}

	private void save() {
		this.carRepository.save(this.newCar);
	}

	private String generateId() {
		Random random = new Random();
		return "C".concat(String.format("%04d", random.nextInt(10000)));
	}

}
