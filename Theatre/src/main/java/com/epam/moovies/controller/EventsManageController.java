package com.epam.moovies.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.epam.moovies.enums.Rating;
import com.epam.moovies.model.Event;
import com.epam.moovies.service.AuditoriumService;
import com.epam.moovies.service.EventService;

@Controller
@RequestMapping("events")
public class EventsManageController {
	@Autowired
	private EventService eventService;
	@Autowired
	private AuditoriumService auditoriumService;

	@RequestMapping("manage")
	public ModelAndView openManageEvents() {
		ModelAndView openManageEventsModelAndView = new ModelAndView("events/manage");
		List<Event> allEvents = eventService.getAll();
		openManageEventsModelAndView.addObject("allEvents", allEvents);
		return openManageEventsModelAndView;
	}

	@RequestMapping("manage/add")
	public ModelAndView openAddEventPage() {
		ModelAndView openAddEventPageModelAndView = new ModelAndView("events/addEvent");
		openAddEventPageModelAndView.addObject("ratingOptions", Rating.values());
		openAddEventPageModelAndView.addObject("auditoriums", auditoriumService.getAll());
		return openAddEventPageModelAndView;
	}

}
