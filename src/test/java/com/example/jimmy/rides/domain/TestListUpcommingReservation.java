package com.example.jimmy.rides.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureJdbc;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.jimmy.rides.application.CreateCar.CreateCar;
import com.example.jimmy.rides.application.CreateCar.dto.CreateCarDto;
import com.example.jimmy.rides.application.ListReservations.ListUpcommingReservations;
import com.example.jimmy.rides.application.ReserveCar.ReserveCar;
import com.example.jimmy.rides.application.ReserveCar.dto.ReserveCarDto;
import com.example.jimmy.rides.domain.Entity.Car;
import com.example.jimmy.rides.domain.Entity.Reservation;
import com.example.jimmy.rides.domain.errors.BeyondDatePermittedException;
import com.example.jimmy.rides.domain.errors.PeriodNotValidException;

import jakarta.transaction.Transactional;

@AutoConfigureJdbc
@SpringBootTest
class TestListUpcommingReservation {
  @Autowired
  CreateCar createCar;

  @Autowired
  ReserveCar reserveCar;

  @Autowired
  ListUpcommingReservations listUpcommingReservations;

  @Transactional
	@Test
  void ShouldListOnlyUpcommingDates() throws BeyondDatePermittedException, PeriodNotValidException, Exception {
		//Car initiaization
		CreateCarDto carDto = new CreateCarDto("OPEL","K1");
		Car car1 = this.createCar.execute(carDto);

		carDto = new CreateCarDto("Toyota","Corolla");
		Car car2 = this.createCar.execute(carDto);
    
		// Reservation data initialization
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");
		LocalDateTime startDate = LocalDateTime.now().plusHours(23);

// First Reservation
		ReserveCarDto dto = new ReserveCarDto();
		dto.setStartDate(startDate.format(formatter));
		dto.setDuration(1);
    Reservation reservation1 = reserveCar.execute(dto);
    Reservation reservation2 = reserveCar.execute(dto);
    List<Reservation> list = this.listUpcommingReservations.execute();

    System.out.println("RESERAVATIONS UPCOMMNG LIST: " + Arrays.toString(list.toArray()));
    assertEquals(list.size(),2);

  }
}
