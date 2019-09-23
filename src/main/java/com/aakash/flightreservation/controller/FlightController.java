package com.aakash.flightreservation.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aakash.flightreservation.entities.Flight;
import com.aakash.flightreservation.repos.FlightRepository;

@Controller
public class FlightController {
    
	@Autowired
	FlightRepository flightRepository;
	
	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(FlightController.class);
	
	@RequestMapping("findFlights")
	public String findFLights(@RequestParam("from") String from, @RequestParam("to") String to, @RequestParam("departureDate") 
	@DateTimeFormat(pattern = "MM-dd-yyyy" ) Date departureDate, ModelMap modelMap) {
		
		LOGGER.info("Inside findFlights() FROM " +from +"TO" +to +"Departure Date" +departureDate);
		List<Flight> flights = flightRepository.findFlights(from,to,departureDate);	
		modelMap.addAttribute("flights", flights);
		return "displayFlights";
	}
	
	@RequestMapping("admin/showAddFlight") 
		public String showAddFlight() {
			return "addFlight";
		}
	}

 	