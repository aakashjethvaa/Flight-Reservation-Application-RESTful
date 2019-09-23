package com.aakash.flightreservation.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aakash.flightreservation.entities.User;
import com.aakash.flightreservation.repos.UserRepository;
import com.aakash.flightreservation.services.SecurityService;
import com.itextpdf.text.log.LoggerFactory;

@Controller
public class UserController {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	SecurityService securityService;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping("/showReg")
	public String showRegistrationPage() {
		LOGGER.info("Inside showRegistrationPage()");
		return "login/registerUser";
	}
	
	@RequestMapping("/showLogin")
	public String showLoginPage() {
		LOGGER.info("Inside showLogin()");
		return "login/login";
	}
	
	
	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public String register(@ModelAttribute("user") User user) {
		LOGGER.info("Inside register()"+user);
		user.setPassword(encoder.encode(user.getPassword()));
		userRepository.save(user);
		return "login/login";	
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam("email") String email, @RequestParam("password") String password, ModelMap modelMap) {
		LOGGER.info("Inside login()"+email);
		
		boolean loginResponse = securityService.login(email, password);
		LOGGER.info("Inside login() and the email is: " + email);
		if(loginResponse) {
			return "findFlights";
		} else {
		modelMap.addAttribute("msg", "Invalid username or password");
		}
		
		return "login/login";
	}
	
}
