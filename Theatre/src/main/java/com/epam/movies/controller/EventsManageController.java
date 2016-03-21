package com.epam.movies.controller;

import com.epam.movies.enums.Rating;
import com.epam.movies.model.Event;
import com.epam.movies.service.AuditoriumService;
import com.epam.movies.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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
