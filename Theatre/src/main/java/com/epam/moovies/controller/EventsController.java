package com.epam.moovies.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.epam.moovies.model.Event;

@Controller
@RequestMapping("events")
public class EventsController {

	@RequestMapping(path = "add", method = RequestMethod.POST)
	public String addEvent(@ModelAttribute("newEvent") Event event, BindingResult bindingResult) {
		
		return "redirect:event";
	}
}
