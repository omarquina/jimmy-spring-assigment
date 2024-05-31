package com.example.jimmy.rides.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureJdbc;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.jimmy.rides.application.CreateCar.CreateCar;
import com.example.jimmy.rides.application.CreateCar.dto.CreateCarDto;
import com.example.jimmy.rides.application.DeleteCar.DeleteCar;
import com.example.jimmy.rides.application.ReserveCar.ReserveCar;
import com.example.jimmy.rides.application.ReserveCar.dto.ReserveCarDto;
import com.example.jimmy.rides.domain.Entity.Car;
import com.example.jimmy.rides.domain.Entity.Reservation;
import com.example.jimmy.rides.domain.errors.BeforeDatePermittedException;
import com.example.jimmy.rides.domain.errors.BeyondDatePermittedException;
import com.example.jimmy.rides.domain.errors.PeriodNotValidException;

import jakarta.transaction.Transactional;

@AutoConfigureJdbc
@SpringBootTest
class TestReserveCar {

	@Autowired
	private ReserveCar reserveCar;

	@Autowired
	private CreateCar createCar;
	@Autowired
	private DeleteCar deleteCar;

  @Transactional
	@Test
	void ShouldReserveCarIn24hoursFromNowBy1HourDuration() throws Exception {

		//Car initiaization
		CreateCarDto carDto = new CreateCarDto("OPEL","K1");
		Car car1 = this.createCar.execute(carDto);

		carDto = new CreateCarDto("Toyota","Corolla");
		Car car2 = this.createCar.execute(carDto);

		// Reservation data initialization
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");
		LocalDateTime startDate = LocalDateTime.now().plusHours(24);

		// First Reservation
		ReserveCarDto dto = new ReserveCarDto();
		dto.setStartDate(startDate.format(formatter));
		dto.setDuration(1);
    Reservation reservation = reserveCar.execute(dto);
		assertInstanceOf(Reservation.class,reservation);
		assertEquals(car1.id, reservation.getCar().getId(), reservation.toString() + ": was not expected");

		//// SECOND Reservation
    reservation = reserveCar.execute(dto);
		assertInstanceOf(Reservation.class,reservation);
		assertEquals(car2.id,reservation.getCar().getId(), reservation.toString() + ": was not expected");

		//// Third Reservation
    reservation = reserveCar.execute(dto);
		assertNull(reservation,"There must not be any car available");

		this.deleteCar.execute(car1.id);
		this.deleteCar.execute(car2.id);
	}


  @Transactional
	@Test
	void ShouldReserveSameCarWhenReservationNotClash() throws Exception {

		//Car initiaization
		CreateCarDto carDto = new CreateCarDto("OPEL","K1");
		Car car1 = this.createCar.execute(carDto);

		carDto = new CreateCarDto("Toyota","Corolla");
		Car car2 = this.createCar.execute(carDto);

		// Reservation data initialization
		// First Reservation
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");
		LocalDateTime startDate = LocalDateTime.now().plusHours(24);

		ReserveCarDto dto = new ReserveCarDto();
		dto.setStartDate(startDate.format(formatter));
		dto.setDuration(1);
    Reservation reservation = reserveCar.execute(dto);
		assertInstanceOf(Reservation.class,reservation);
		assertEquals(car1.id, reservation.getCar().getId(), reservation.toString() + ": was not expected");

		//// SECOND Reservation
	  dto = new ReserveCarDto();
		startDate = LocalDateTime.now().plusHours(10);
		dto.setStartDate(startDate.format(formatter));
		dto.setDuration(1);

    reservation = reserveCar.execute(dto);
		assertInstanceOf(Reservation.class,reservation);
		assertEquals(car1.id,reservation.getCar().getId(), reservation.toString() + ": was not expected");

			this.deleteCar.execute(car1.id);
			this.deleteCar.execute(car2.id);
	}

  @Transactional
	@Test
	void ShouldNotReserveWhenThereAreNoCars() throws Exception {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");
		LocalDateTime startDate = LocalDateTime.now().plusHours(24);

		ReserveCarDto dto = new ReserveCarDto();
		dto.setStartDate(startDate.format(formatter));
		dto.setDuration(1);
    Reservation reservation = reserveCar.execute(dto);
		assertNull(reservation,"There must not be any car available");
	}


  @Transactional
	@Test
	void ShouldFailIfReserveCarIn25HoursFromNow() throws Exception {

		// Reservation data initialization
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");
		LocalDateTime startDate = LocalDateTime.now().plusHours(25);

		ReserveCarDto dto = new ReserveCarDto();
		dto.setStartDate(startDate.format(formatter));
		dto.setDuration(1);
    
		Exception exception = assertThrows(BeyondDatePermittedException.class, () -> reserveCar.execute(dto));
	}

	@Transactional
	@Test
	void ShouldFailIfReserveCarBy3HourDuration() throws Exception {
		// Reservation data initialization
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");
		LocalDateTime startDate = LocalDateTime.now().plusHours(23);

		// First Reservation
		ReserveCarDto dto = new ReserveCarDto();
		dto.setStartDate(startDate.format(formatter));
		dto.setDuration(3);
    
		Exception exception = assertThrows(PeriodNotValidException.class, () -> reserveCar.execute(dto));
	}

	@Transactional
	@Test
	void ShouldFailToReserveCarIfDateIsBeforeToNow() throws Exception {
		// Reservation data initialization
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");
		LocalDateTime startDate = LocalDateTime.now().minusHours(5);

		// First Reservation
		ReserveCarDto dto = new ReserveCarDto();
		dto.setStartDate(startDate.format(formatter));
		dto.setDuration(1);
    
		Exception exception = assertThrows(BeforeDatePermittedException.class, () -> reserveCar.execute(dto));
	}
}
