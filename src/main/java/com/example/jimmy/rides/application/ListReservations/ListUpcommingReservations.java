package com.example.jimmy.rides.application.ListReservations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.jimmy.rides.domain.Entity.Reservation;
import com.example.jimmy.rides.infraestructure.repositories.ReservationRepositoryJpa;

@Component
public class ListUpcommingReservations {

@Autowired
  ReservationRepositoryJpa reservationRepository;

	public List<Reservation> execute() {

		return this.reservationRepository.getUpcommingReservations();
	}
}
