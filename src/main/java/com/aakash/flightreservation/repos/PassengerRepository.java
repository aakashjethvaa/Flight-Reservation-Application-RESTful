package com.aakash.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aakash.flightreservation.entities.Passenger;
import com.aakash.flightreservation.entities.User;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

	
}
