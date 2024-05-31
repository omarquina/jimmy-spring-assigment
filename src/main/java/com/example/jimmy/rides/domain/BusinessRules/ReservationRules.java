package com.example.jimmy.rides.domain.BusinessRules;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.springframework.data.domain.Range;

public class ReservationRules {
	private static final DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm"); 
  private static final Integer maxHoursAheadToReserve = 24;
	private static final Integer maxMinutesForReservationPeriod = 120;


	public static LocalDateTime getMaxDateAccepted() {
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime maxDateAccepted = now.plusHours(ReservationRules.maxHoursAheadToReserve).truncatedTo(ChronoUnit.MINUTES);	 

		return  maxDateAccepted;
	}

	public static LocalDateTime getMinDateAccepted() {
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime minDateAccepted = now.truncatedTo(ChronoUnit.MINUTES);	 

		return  minDateAccepted;
	}

	public static LocalDateTime parseStartDate(String startDate) {
		LocalDateTime result = LocalDateTime.parse(startDate,ReservationRules.formatterDate);
		return result; 
	}
	
	public static String formatStartDate(LocalDateTime startDate) {
		return startDate.format(ReservationRules.formatterDate);
	}

	public static Boolean isValidDuration(Integer duration) {
    return ReservationRules.durationRange().contains(duration);
	}

	public static Range<Integer> durationRange(){
		Range<Integer> durationRange = Range.closed(1,maxMinutesForReservationPeriod);
		return durationRange;
	}
}
