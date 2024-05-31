package com.example.jimmy.rides.domain;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureJdbc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.example.jimmy.rides.application.CreateCar.CreateCar;
import com.example.jimmy.rides.application.CreateCar.dto.CreateCarDto;
import com.example.jimmy.rides.domain.Entity.Car;

@AutoConfigureJdbc
@SpringBootTest
class CreateCarTest {

	@Autowired
	private CreateCar createCar;

	@Test
	void ShoudCreateCarWithIdPattern() throws Exception {
		CreateCarDto dto = new CreateCarDto("Toyota","Corolla");
		Car response = this.createCar.execute(dto);
    String carId = response.getId();
		assertNotNull(carId);
		
		Pattern pattern = Pattern.compile("C\\d{4}" );
    Matcher matcher = pattern.matcher(carId);
		Assert.isTrue(matcher.find(),carId + ": must copmply with C#### pattern" );
	}

}
