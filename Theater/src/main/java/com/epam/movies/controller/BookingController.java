package com.epam.movies.controller;

import com.epam.movies.model.Event;
import com.epam.movies.model.Ticket;
import com.epam.movies.model.User;
import com.epam.movies.service.AuditoriumService;
import com.epam.movies.service.BookingService;
import com.epam.movies.service.EventService;
import com.epam.movies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
		return "redirect:/tickets/my";
	}

}
