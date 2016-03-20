package com.epam.moovies.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.epam.moovies.model.Ticket;
import com.epam.moovies.model.User;
import com.epam.moovies.service.BookingService;
import com.epam.moovies.service.UserService;

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

}
