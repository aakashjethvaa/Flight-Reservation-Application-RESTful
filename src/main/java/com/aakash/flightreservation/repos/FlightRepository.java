package com.aakash.flightreservation.repos;

import java.util.Date;
import java.util.List;

import org.jboss.logging.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.aakash.flightreservation.entities.Flight;
import com.aakash.flightreservation.entities.User;

public interface FlightRepository extends JpaRepository<Flight, Long> {

	@Query("from Flight where departureCity=:departureCity and arrivalCity=:arrivalCity and dateOfDeparture = :dateOfDeparture")
	List<Flight> findFlights(@org.springframework.data.repository.query.Param("departureCity") String from, @org.springframework.data.repository.query.Param("arrivalCity")String to, 
			@org.springframework.data.repository.query.Param("dateOfDeparture") Date departureDate);

	
}
