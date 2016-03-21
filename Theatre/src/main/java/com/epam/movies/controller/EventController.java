package com.epam.movies.controller;

import com.epam.movies.model.Event;
import com.epam.movies.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("event")
public class EventController {

	@Autowired
	private EventService eventService;

	@RequestMapping("/{eventId}")
	public ModelAndView openEventPage(@PathVariable("eventId") Long eventId) {
		ModelAndView openEventPageModelAndView = new ModelAndView("events/event");
		Event eventById = eventService.getById(eventId);
		openEventPageModelAndView.addObject("event", eventById);
		return openEventPageModelAndView;
	}
}
