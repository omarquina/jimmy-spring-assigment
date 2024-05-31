package com.example.jimmy.rides.infraestructure.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.jimmy.rides.domain.Entity.Reservation;

@Repository
public interface ReservationRepositoryJpa extends JpaRepository<Reservation,Long>{
//RESERVATIONS.ID, RESERVATIONS.CAR_ID,RESERVATIONS.START_DATE, RESERVATIONS.END_DATE, RESERVATIONS.DURATION, CARS.ID, CARS.MAKE, CARS.MODEL
	@Query(nativeQuery = true, value = "SELECT RESERVATIONS.* from RESERVATIONS INNER JOIN CARS ON CARS.ID=RESERVATIONS.CAR_ID WHERE RESERVATIONS.START_DATE >= now()")
   List<Reservation> getUpcommingReservations();
}
