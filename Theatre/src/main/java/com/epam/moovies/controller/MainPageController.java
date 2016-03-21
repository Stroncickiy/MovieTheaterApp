package com.epam.moovies.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.epam.moovies.model.Event;
import com.epam.moovies.service.EventService;

@Controller
public class MainPageController {

	@Autowired
	private EventService eventService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView indexModelAndView = new ModelAndView("index");
		List<Event> all = eventService.getAll();
		indexModelAndView.addObject("events", all);
		return indexModelAndView;
	}

	@RequestMapping(value = "/errorTest", method = { RequestMethod.GET })
	public void openPageWithException() throws IOException {
		throw new IOException("some Error occured in this controller");

	}

}
