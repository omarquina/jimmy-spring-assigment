package com.example.jimmy.rides.application.CreateCar.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarDto {
	@Schema(example="Toyota")
	String make;
	@Schema(example="Corolla")
	String model;

	public String getMake(){
		return this.make;
	}

	public String getModel(){
		return this.model;
	}

	public void setModel(String model){
		this.model = model;
	}

	public void setMake(String make){
		this.make = make;
	}
}
