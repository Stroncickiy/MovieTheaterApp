package com.epam.moovies.controller;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.epam.moovies.converters.LocalDateTimeConverter;
import com.epam.moovies.enums.Rating;
import com.epam.moovies.model.Auditorium;
import com.epam.moovies.model.Event;
import com.epam.moovies.service.AuditoriumService;
import com.epam.moovies.service.EventService;

@Controller
@RequestMapping("events")
public class EventsController {

	@Autowired
	private AuditoriumService auditoryService;

	@Autowired
	private EventService eventService;

	@RequestMapping(path = "add", method = RequestMethod.POST)
	public String addEvent(@RequestParam Map<String, String> allRequestParams) {
		Event newEvent = new Event();
		// get auditorium id from request
		String auditoryIdString = allRequestParams.get("auditorium");
		Long auditoryId = Long.parseLong(auditoryIdString);
		Auditorium auditorium = auditoryService.getById(auditoryId);
		newEvent.setAuditorium(auditorium);
		// get name
		String name = allRequestParams.get("name");
		newEvent.setName(name);
		// get base price
		String basePriceString = allRequestParams.get("basePrice");
		long basePrice = Long.parseLong(basePriceString);
		newEvent.setBasePrice(basePrice);

		// get start
		String start = allRequestParams.get("start");
		LocalDateTime startDateTime = LocalDateTimeConverter.convert(start);
		newEvent.setStart(startDateTime);

		// get stop
		String end = allRequestParams.get("end");
		LocalDateTime endDateTime = LocalDateTimeConverter.convert(end);
		newEvent.setEnd(endDateTime);

		String ratingString = allRequestParams.get("rating");
		Rating rating = Rating.valueOf(ratingString);
		newEvent.setRating(rating);

		Event createdEvent = eventService.create(newEvent);
		return "redirect:/event/" + createdEvent.getId();
	}
}
