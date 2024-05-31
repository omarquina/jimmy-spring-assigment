package com.example.jimmy.rides.infraestructure.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.jimmy.rides.application.ListReservations.ListUpcommingReservations;
import com.example.jimmy.rides.application.ReserveCar.ReserveCar;
import com.example.jimmy.rides.application.ReserveCar.dto.ReserveCarDto;
import com.example.jimmy.rides.domain.Entity.Reservation;

import io.swagger.v3.oas.annotations.tags.Tag;

	@RestController
	@RequestMapping("/api/reservations")
	@Tag(name = "Reservations API")
	class ReservationController {
		
		@Autowired
		ReserveCar reserveCar;

		@Autowired
		ListUpcommingReservations listReservation;

		@PostMapping
		public ResponseEntity createReservation(@RequestBody ReserveCarDto dto){
			try {
				Reservation newReservation = this.reserveCar.execute(dto);
				return ResponseEntity.status(HttpStatus.CREATED).body(newReservation);
			} catch(Exception error){
				 return ResponseEntity
            .status(HttpStatus.FORBIDDEN)
            .body(error.getMessage());
			}
		}

    @GetMapping
		public List<Reservation> listReservation() {
		  return this.listReservation.execute();
		}
	} 
