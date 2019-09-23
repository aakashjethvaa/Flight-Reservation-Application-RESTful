package com.aakash.flightreservation.util;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.aakash.flightreservation.controller.ReservationController;

@Component
public class EmailUtil {
	
	@Autowired
	private JavaMailSender sender;
	
	@Value("${com.aakash.flightreservation.itinerary.email.body}")
	private String EMAIL_BODY = "Please find your Itinerary attached.";
	
	@Value("${com.aakash.flightreservation.itinerary.email.subject}")
	private String EMAIL_SUBJECT = "Itinerary for your Flight";
	
	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(EmailUtil.class);


	public void sendItinerary(String toAddress, String filePath) {

		LOGGER.info("Send Itinerary()");
		MimeMessage message = sender.createMimeMessage();

		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
			messageHelper.setTo(toAddress);
			messageHelper.setSubject(EMAIL_SUBJECT);
			messageHelper.setText(EMAIL_BODY);
			messageHelper.addAttachment("Itinerary", new File(filePath));
			sender.send(message);
		} catch (MessagingException e) {
			LOGGER.error("Exception inside the sendItinerary" + e);
		}
	}

}