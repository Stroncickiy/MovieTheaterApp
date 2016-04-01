package com.epam.movies.controller;

import com.epam.movies.model.Event;
import com.epam.movies.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

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

    @RequestMapping(value = "/witherror", method = {RequestMethod.GET})
    public void openPageWithException() throws IOException {
        throw new IOException("some Error occured in this controller");

    }

}
