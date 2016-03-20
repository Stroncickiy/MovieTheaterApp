package com.epam.moovies.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.epam.moovies.model.Event;
import com.epam.moovies.model.Ticket;
import com.epam.moovies.model.User;
import com.epam.moovies.service.AuditoriumService;
import com.epam.moovies.service.BookingService;
import com.epam.moovies.service.EventService;
import com.epam.moovies.service.UserService;

@Controller
@RequestMapping("ticket")
public class BookingController {

	@Autowired
	private UserService userService;

	@Autowired
	private EventService eventService;

	@Autowired
	private AuditoriumService auditoriumService;

	@Autowired
	private BookingService bookingService;

	@RequestMapping(value = "/book/{id}", method = RequestMethod.POST)
	public String bookPlaceForEvent(@PathVariable("id") long id,
			@RequestParam("targetSeats") List<String> chosenSeatsStrings) {
		User customer = userService.getAll().get(0);
		Event targetEvent = eventService.getById(id);

		Long[] chosenSeats = new Long[chosenSeatsStrings.size()];
		for (int i = 0; i < chosenSeatsStrings.size(); i++) {
			chosenSeats[i] = Long.parseLong(chosenSeatsStrings.get(i));
		}

		Ticket ticket = new Ticket();
		ticket.setEvent(targetEvent);
		ticket.setCustomer(customer);
		ticket.setBookedSeats(
				auditoriumService.getSeatsByNumbersAndAuditorium(targetEvent.getAuditorium().getId(), chosenSeats));
		bookingService.bookTicket(ticket);
		return "tickets/userTickets";
	}

}
