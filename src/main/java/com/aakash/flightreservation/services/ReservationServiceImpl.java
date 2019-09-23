package com.aakash.flightreservation.services;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.aakash.flightreservation.controller.ReservationController;
import com.aakash.flightreservation.dto.ReservationRequest;
import com.aakash.flightreservation.entities.Flight;
import com.aakash.flightreservation.entities.Passenger;
import com.aakash.flightreservation.entities.Reservation;
import com.aakash.flightreservation.repos.FlightRepository;
import com.aakash.flightreservation.repos.PassengerRepository;
import com.aakash.flightreservation.repos.ReservationRepository;
import com.aakash.flightreservation.util.EmailUtil;
import com.aakash.flightreservation.util.PDFGenerator;

@Service
public class ReservationServiceImpl implements ReservationService {
	
	@Value("${com.aakash.flightreservation.itinerary.dirpath}")
	private String ITINERARY_DIR;

	@Autowired
	FlightRepository flightRepository;
	
	@Autowired
	PassengerRepository passengerRepository;
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	PDFGenerator pdfGenerator;
	
	@Autowired
	EmailUtil emailUtil;
	
	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ReservationServiceImpl.class);
	
	@Override
	@Transactional
	public Reservation bookFlight(ReservationRequest request) {
		
		LOGGER.info("Inside bookFlight");
		Long flightId = request.getFlightId();
		LOGGER.info("Fetching the flightId" +flightId);
		Flight flight = flightRepository.findById(flightId).get();
		
		Passenger passenger = new Passenger();
		passenger.setFirstName(request.getPassenegerFirstName());
		passenger.setLastName(request.getPassengerLastName());
		passenger.setPhone(request.getPassengerPhone());
		passenger.setEmail(request.getPassengerEmail());
		LOGGER.info("Saving the passenger:"+ passenger);
		Passenger savedPassenger = passengerRepository.save(passenger);
		
		
		Reservation reservation = new Reservation();
		reservation.setFlight(flight);
		reservation.setPassenger(passenger);
		reservation.setCheckedIn(false);
		LOGGER.info("Saving the reservation" +reservation);
		Reservation savedReservation = reservationRepository.save(reservation);

		String filePath = ITINERARY_DIR + savedReservation.getId() + ".pdf";
		pdfGenerator.generateItinerary(savedReservation, filePath);
		emailUtil.sendItinerary(passenger.getEmail(), filePath);
		
		return savedReservation;
	}

}
