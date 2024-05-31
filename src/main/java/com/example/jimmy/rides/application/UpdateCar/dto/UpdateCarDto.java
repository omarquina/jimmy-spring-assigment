package com.example.jimmy.rides.application.UpdateCar.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarDto {
	@Schema(example="Toyota")
	String make;
	@Schema(example="Corolla")
	String model;
	String id;
	
	public String getId(){
		return this.id;
	}

	public void setId(String id){
		this.id = id;
	}

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
