package com.example.jimmy.rides.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureJdbc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.example.jimmy.rides.application.CreateCar.CreateCar;
import com.example.jimmy.rides.application.CreateCar.dto.CreateCarDto;
import com.example.jimmy.rides.application.UpdateCar.UpdateCar;
import com.example.jimmy.rides.application.UpdateCar.dto.UpdateCarDto;
import com.example.jimmy.rides.domain.Entity.Car;

@AutoConfigureJdbc
@SpringBootTest
class TestUpdateCar {

	@Autowired
	private CreateCar createCar;
	@Autowired
	private UpdateCar updateCar;
	
	//@Autowired
	//private ListCars listCars;
	

	@Transactional
	@Test
	void ShoudUpdateCarModelAndMake() throws Exception {
		CreateCarDto createCarDto = new CreateCarDto("TESLA","M3");
		Car car = this.createCar.execute(createCarDto);
    String updateMake = "Toyota";
		String updateModel = "Corolla";
		UpdateCarDto dto = new UpdateCarDto(updateMake,updateModel,car.getId());
		Car response = this.updateCar.execute(dto);
    String carId = response.getId();
		assertEquals(response.getId(), car.getId());
		assertEquals(response.getMake(),updateMake);
		assertEquals(response.getModel(),updateModel);
		
	}

	@Transactional
	@Test
	void ShoudUpdateOnlyCarModel() throws Exception {
		CreateCarDto createCarDto = new CreateCarDto("TESLA","M3");
		Car car = this.createCar.execute(createCarDto);
		String updateModel = "Corolla";
		UpdateCarDto dto = new UpdateCarDto();
		dto.setModel(updateModel);
		dto.setId(car.id);
		Car response = this.updateCar.execute(dto);
    String carId = response.getId();
		assertEquals(response.getId(), car.getId());
		assertEquals(response.getMake(),car.getMake());
		assertEquals(response.getModel(),updateModel);
	}

	@Transactional
	@Test
	void ShoudUpdateOnlyCarMake() throws Exception {
		CreateCarDto createCarDto = new CreateCarDto("TESLA","M3");
		Car car = this.createCar.execute(createCarDto);
		String updateMake = "Toyota";
		UpdateCarDto dto = new UpdateCarDto();
		dto.setMake(updateMake);
		dto.setId(car.id);
		Car response = this.updateCar.execute(dto);
    String carId = response.getId();
		assertEquals(response.getId(), car.getId());
		assertEquals(response.getMake(),updateMake);
		assertEquals(response.getModel(),car.getModel());
	}
}
