package com.example.jimmy.rides.domain.errors;

public class PeriodNotValidException extends Exception {

	public PeriodNotValidException(String durationRange) {
		super("Duration must be between: " + durationRange);

	}
}
