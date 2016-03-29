package com.epam.movies.controller;

import com.epam.movies.model.Ticket;
import com.epam.movies.model.User;
import com.epam.movies.service.BookingService;
import com.epam.movies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("tickets")
public class TicketsController {

	@Autowired
	private UserService userService;

	@Autowired
	private BookingService bookingService;

	@RequestMapping("/my")
	public ModelAndView openMyTicketsPage() {
		ModelAndView modelAndView = new ModelAndView("tickets/userTickets");
		User user = userService.getAll().get(0);
		List<Ticket> ticketsForUser = bookingService.getTicketsForUser(user);
		modelAndView.addObject("tickets", ticketsForUser);
		return modelAndView;
	}

	@RequestMapping(path = "/my/get", produces = { "application/pdf" })
	public ModelAndView getTicketsAsFile() {
		User user = userService.getAll().get(0);
		List<Ticket> ticketsForUser = bookingService.getTicketsForUser(user);
		return new ModelAndView("ticketsPdfView", "tickets", ticketsForUser);
	}



}
