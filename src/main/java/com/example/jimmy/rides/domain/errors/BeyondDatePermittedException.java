package com.example.jimmy.rides.domain.errors;

public class BeyondDatePermittedException extends Exception {
  private String invalidDate; 

	public BeyondDatePermittedException(String invalidDate) {
		super("The date is not permitted, choose one earlier than " + invalidDate );

	  this.invalidDate = invalidDate;
	}
}
