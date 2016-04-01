package com.epam.movies.controller;

import com.epam.movies.converters.LocalDateTimeConverter;
import com.epam.movies.enums.Rating;
import com.epam.movies.model.Auditorium;
import com.epam.movies.model.Event;
import com.epam.movies.service.AuditoriumService;
import com.epam.movies.service.EventService;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("events")
public class EventController {


    @Autowired
    private AuditoriumService auditoryService;

    @Autowired
    private EventService eventService;

    @Autowired
    private AuditoriumService auditoriumService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String openAllEvents(Model model) {
        model.addAttribute("events", eventService.getAll());
        return "events/allEvents";
    }

    @RequestMapping(value = "/loadFromFile", method = RequestMethod.POST)
    public String loadEventsFromXml(@RequestParam("fileWithEvents") MultipartFile file) throws IOException {
        XStream xStream = new XStream(new StaxDriver());
        @SuppressWarnings("unchecked")
        List<Event> events = (List<Event>) xStream.fromXML(file.getInputStream());
        for (Event event : events) {
            eventService.create(event);
        }
        return "redirect:/events/all";
    }

    @RequestMapping(value = "/all/download", method = RequestMethod.GET)
    public void getEventsInXml(HttpServletResponse response) throws IOException {
        List<Event> all = eventService.getAll();
        XStream xStream = new XStream(new StaxDriver());
        response.setHeader("content-type", "application/xml");
        String xml = xStream.toXML(all);
        response.getWriter().write(xml);
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
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
        return "redirect:/events/" + createdEvent.getId();
    }

    @RequestMapping("book/{eventId}")
    public String openBookTicketForEventPage(@PathVariable("eventId") Long eventId, Model model) {
        model.addAttribute("event", eventService.getById(eventId));
        return "events/book";
    }


    @RequestMapping("/{eventId}")
    public String openEventPage(@PathVariable("eventId") Long eventId, Model model) {
        model.addAttribute("event", eventService.getById(eventId));
        return "events/event";
    }

    @RequestMapping("manage")
    public ModelAndView openManageEvents() {
        ModelAndView openManageEventsModelAndView = new ModelAndView("events/manage");
        List<Event> allEvents = eventService.getAll();
        openManageEventsModelAndView.addObject("allEvents", allEvents);
        return openManageEventsModelAndView;
    }

    @RequestMapping("add")
    public ModelAndView openAddEventPage() {
        ModelAndView openAddEventPageModelAndView = new ModelAndView("events/addEvent");
        openAddEventPageModelAndView.addObject("ratingOptions", Rating.values());
        openAddEventPageModelAndView.addObject("auditoriums", auditoriumService.getAll());
        return openAddEventPageModelAndView;
    }
}
