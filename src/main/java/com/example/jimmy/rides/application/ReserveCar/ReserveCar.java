package com.example.jimmy.rides.application.ReserveCar;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Component;

import com.example.jimmy.rides.application.ReserveCar.dto.ReserveCarDto;
import com.example.jimmy.rides.domain.Entity.Car;
import com.example.jimmy.rides.domain.Entity.Reservation;
import com.example.jimmy.rides.domain.errors.BeforeDatePermittedException;
import com.example.jimmy.rides.domain.errors.BeyondDatePermittedException;
import com.example.jimmy.rides.domain.errors.PeriodNotValidException;
import com.example.jimmy.rides.infraestructure.repositories.CarRepositoryJpa;
import com.example.jimmy.rides.infraestructure.repositories.ReservationRepositoryJpa;
import com.example.jimmy.rides.domain.BusinessRules.ReservationRules;

@Component
public class ReserveCar {
	
	@Autowired
	CarRepositoryJpa carRepository;

  @Autowired
	ReservationRepositoryJpa reservationRepository;

  private ReserveCarDto dto;
  private LocalDateTime startDate = null;
  private LocalDateTime endDate = null;
	private Integer duration = 1;
	private Car availableCar = null; 
  private LocalDateTime maxDateAccepted = null;
	private Reservation reservation = null;

	public Reservation execute(ReserveCarDto dto) throws BeyondDatePermittedException, PeriodNotValidException, BeforeDatePermittedException {
		try {
			this.prepareData(dto);
      this.validate();
			this.getAvailableCar();
			this.createReservation();
			return this.reservation;
		} catch(BeyondDatePermittedException | PeriodNotValidException error){
			System.err.println("ERROR: " + error.getMessage());
			throw error;
		}
	}
  
	protected void prepareData(ReserveCarDto dto) {
    this.dto = dto;
		this.reservation = null;
		this.availableCar = null;
		this.duration = dto.getDuration();
    String startDateString = (String) dto.getStartDate();
		this.startDate = ReservationRules.parseStartDate(startDateString); 
		this.endDate = this.startDate.plusHours(this.duration);
	}

	protected void validate() throws BeyondDatePermittedException, PeriodNotValidException, BeforeDatePermittedException {
    this.validateStartDate();
		this.validateDuration();
	}

	private void validateDuration() throws PeriodNotValidException {
		if (!ReservationRules.isValidDuration(this.duration) )
			throw new PeriodNotValidException(ReservationRules.durationRange().toString());
	}

	private void getAvailableCar() {
    List<Car> availableCars = this.carRepository.findAvailableByDate(this.startDate);
		System.out.println("Available " + availableCars);
		if (!availableCars.isEmpty()) 
			this.availableCar = availableCars.get(0);	
	}

	private void validateStartDate() throws BeyondDatePermittedException, BeforeDatePermittedException {
		if (this.startDate.isBefore(ReservationRules.getMinDateAccepted())){
			String responseDateError = ReservationRules.formatStartDate(this.startDate);
			throw new BeforeDatePermittedException(responseDateError);
		}


		if(this.startDate.isAfter(ReservationRules.getMaxDateAccepted())){
			String responseDateError = ReservationRules.formatStartDate(this.startDate);
			throw new BeyondDatePermittedException(responseDateError);
		}
	}

	private void createReservation() {
			if (this.availableCar == null) return;

			Reservation reservation = new Reservation();
			reservation.setStartDate(this.startDate);
		  reservation.setEndDate(this.endDate);
			reservation.setDuration(this.duration);
			reservation.setCar(this.availableCar);
			this.reservation = this.reservationRepository.save(reservation);
		}
}
