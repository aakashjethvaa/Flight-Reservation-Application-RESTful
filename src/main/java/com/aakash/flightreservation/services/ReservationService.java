package com.aakash.flightreservation.services;

import com.aakash.flightreservation.dto.ReservationRequest;
import com.aakash.flightreservation.entities.Reservation;

public interface ReservationService {

	public Reservation bookFlight(ReservationRequest request);
	
	
}
