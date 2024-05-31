package com.example.jimmy.rides.domain.Entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import jakarta.persistence.*;

@Entity
@Table(name="cars")
@ToString
public class Car {

	@Id
	public String id;

	private String make;

	private String model;

	public void setMake(String make) {
     this.make = make;
	}

	public String getMake() {
     return this.make;
	}

	public void setModel(String model) {
     this.model = model;
	}

	public String getModel() {
     return this.model;
	}

	public void setId(String id) {
     this.id = id;
	}

	public String getId() {
     return this.id;
	}

}
