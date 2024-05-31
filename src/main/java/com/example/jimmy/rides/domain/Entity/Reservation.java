package com.example.jimmy.rides.domain.Entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.*;

@Entity
@Table(name="reservations")
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	private int id;

	@Getter @Setter
	private LocalDateTime startDate;

	@Getter @Setter
	private LocalDateTime endDate;

	@Getter @Setter
	private int duration;

	@ManyToOne
	@JoinColumn(name="car_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@Getter @Setter
	private Car car;
	
}
