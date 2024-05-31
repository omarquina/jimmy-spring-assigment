package com.example.jimmy.rides.infraestructure.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.jimmy.rides.domain.Entity.Car;

@Repository
public interface CarRepositoryJpa extends JpaRepository<Car,String>{
	@Query(nativeQuery = true, value = "select cars.* from cars as cars left join reservations as reservations on cars.id = reservations.car_id where cars.id not in (select CARS2.id from cars as CARS2 inner join reservations as reservations2 on cars2.id = reservations2.car_id where :date between reservations2.start_date and reservations2.end_date )")
			//List<Car> findAvailableByDate(@Param("date") String date);
	    List<Car> findAvailableByDate(@Param("date") LocalDateTime date);
}
