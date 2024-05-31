package com.example.jimmy.rides.domain.errors;

public class BeforeDatePermittedException extends Exception {
  private String invalidDate; 

	public BeforeDatePermittedException(String invalidDate) {
		super("The date is not valid, choose a later one than: " + invalidDate );

	  this.invalidDate = invalidDate;
	}
}
