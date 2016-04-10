package com.epam.movies.controller.rest;

import com.epam.movies.model.Event;
import com.epam.movies.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rest")
public class EventRESTController {

    @Autowired
    private EventService eventService;

    @RequestMapping(path = "event", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Event>> allUsers() {
        List<Event> all = eventService.getAll();
        if (all.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(all, HttpStatus.OK);
    }


    @RequestMapping(path = "event", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Event> createEvent(@RequestBody Event eventToCreate) {
        Event createdEvent = eventService.create(eventToCreate);
        if (createdEvent == null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
    }

    @RequestMapping(path = "event/{eventId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Event> getEvent(@PathVariable Long eventId) {
        Event event = eventService.getById(eventId);
        if (event == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(event, HttpStatus.OK);
    }


    @RequestMapping(path = "event/{eventId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Event> updateUser(@PathVariable Long eventId, @RequestBody Event event) {
        boolean updated = eventService.update(event);
        if (updated) {
            Event updatedEvent = eventService.getById(eventId);
            return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(path = "event/{eventId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteEvent(@PathVariable Long eventId) {
        boolean removed = eventService.remove(eventId);
        if (removed) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
