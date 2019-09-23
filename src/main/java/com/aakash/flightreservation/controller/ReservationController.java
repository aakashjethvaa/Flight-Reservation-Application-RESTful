package com.aakash.flightreservation.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aakash.flightreservation.dto.ReservationRequest;
import com.aakash.flightreservation.entities.Flight;
import com.aakash.flightreservation.entities.Reservation;
import com.aakash.flightreservation.repos.FlightRepository;
import com.aakash.flightreservation.services.ReservationService;

@Controller
public class ReservationController {

	@Autowired
	FlightRepository flightRepository;
	
	@Autowired
	ReservationService reservationService;
	
	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ReservationController.class);

	
	@RequestMapping("/showCompleteReservation") 
		public String showCompleteReservation(@RequestParam("flightId") Long flightId, ModelMap modelMap) {
	        LOGGER.info("showCompleteReservation()" +flightId);	
			Flight flight = flightRepository.findById(flightId).get();
			modelMap.addAttribute("flight",flight);
			return "completeReservation";
		}
	
	@RequestMapping(value="/completeReservation" ,method= RequestMethod.POST) 
		public String completeReservation(ReservationRequest request, ModelMap modelMap) {
		LOGGER.info("completeReservation()");
		Reservation reservation = reservationService.bookFlight(request);
	    modelMap.addAttribute("msg", "Reservation create successfully"+ reservation.getId());
		return "reservationConfirmation";
		
		}
	}


