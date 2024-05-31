package com.example.jimmy.rides.application.UpdateCar;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.jimmy.rides.application.CreateCar.dto.CreateCarDto;
import com.example.jimmy.rides.application.UpdateCar.dto.UpdateCarDto;
import com.example.jimmy.rides.domain.Entity.Car;
import com.example.jimmy.rides.infraestructure.repositories.CarRepositoryJpa;

@Component
public class UpdateCar {
	@Autowired
	private CarRepositoryJpa carRepository;
	private Car updateCar;
	private UpdateCarDto dto;

	public Car execute(UpdateCarDto carDto) throws Exception {
		try {
			this.preprocessData(carDto);
			this.validate();
			this.save();
			return this.updateCar;
			

		} catch(Exception error) {
			System.out.println(error.getMessage());
			throw error;
		}
	}

	private void preprocessData(UpdateCarDto dto) throws Exception {
		this.dto = dto;
		System.out.println("Prep update:  " + this.dto.toString());
		this.updateCar = this.carRepository.findById(this.dto.getId()).orElse(null);
		System.out.println("Prep update updatedCar: " + this.updateCar);
		if (this.updateCar == null) {
			throw new Exception("Car with " + this.dto.getId() + " does not exists!");
		}

		this.updateCar.setId((String)dto.getId());
		if ( dto.getMake() != null )
			this.updateCar.setMake(dto.getMake());

		if ( dto.getModel() != null )
			this.updateCar.setModel(dto.getModel());
	}

		private void validate() throws Exception {
			if (this.updateCar.getMake() == "" || this.updateCar.getModel() == "" ){
				throw new Exception("Make and Model must not be empty");
			}
		}


	private void save() {

		this.carRepository.save(this.updateCar);
	}

}
