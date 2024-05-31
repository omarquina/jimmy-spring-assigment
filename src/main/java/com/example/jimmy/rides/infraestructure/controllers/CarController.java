package com.example.jimmy.rides.infraestructure.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jimmy.rides.application.CreateCar.CreateCar;
import com.example.jimmy.rides.application.CreateCar.dto.CreateCarDto;
import com.example.jimmy.rides.application.DeleteCar.DeleteCar;
import com.example.jimmy.rides.application.ListCars.ListCars;
import com.example.jimmy.rides.application.UpdateCar.UpdateCar;
import com.example.jimmy.rides.application.UpdateCar.dto.UpdateCarDto;
import com.example.jimmy.rides.domain.Entity.Car;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/cars")
@Tag(name = "Cars API")
public class CarController {

	@Autowired
	private CreateCar createCar;

	@Autowired
	private UpdateCar updateCar;

	@Autowired
	private DeleteCar deleteCar;

	@Autowired
	ListCars listCars;

	@CrossOrigin
	@PostMapping
	public ResponseEntity createCar(@RequestBody CreateCarDto carDto) {
		try {
			Car newCar = this.createCar.execute(carDto);
			return ResponseEntity.status(HttpStatus.CREATED).body(newCar);
		} catch (Exception error) {
			 return ResponseEntity
            .status(HttpStatus.FORBIDDEN)
            .body(error.getMessage());
		}
 }

 @CrossOrigin
 @GetMapping
 public List<Car> listall() {
    return this.listCars.execute();
 }

 @CrossOrigin
 @PatchMapping("/{id}")
 public ResponseEntity updateCar(@PathVariable String id, @RequestBody UpdateCarDto dto){
   try {
		 dto.setId(id);
		 Car updatedCar = this.updateCar.execute(dto);
		 return ResponseEntity.ok(updatedCar);
	 } catch (Exception error) {
		 return ResponseEntity
            .status(HttpStatus.FORBIDDEN)
            .body(error.getMessage());
		}
 }

 @CrossOrigin
 @DeleteMapping("/{id}")
 //public ResponseEntity<Void> deleteCar(@PathVariable String id) throws Exception {
 public ResponseEntity deleteCar(@PathVariable String id) throws Exception {
   try {
		 this.deleteCar.execute(id);
		 return ResponseEntity.ok().build();
	 } catch (Exception error) {
		 return ResponseEntity
            .status(HttpStatus.FORBIDDEN)
            .body(error.getMessage());
		}
 }

}
