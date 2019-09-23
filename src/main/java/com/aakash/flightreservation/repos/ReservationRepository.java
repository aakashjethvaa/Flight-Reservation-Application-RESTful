package com.aakash.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aakash.flightreservation.entities.Reservation;
import com.aakash.flightreservation.entities.User;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	
}
