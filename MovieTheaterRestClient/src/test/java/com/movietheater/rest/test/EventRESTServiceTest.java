package com.movietheater.rest.test;

import com.epam.movies.enums.Rating;
import com.epam.movies.model.Auditorium;
import com.epam.movies.model.Event;
import com.epam.movietheater.configuration.AppConfig;
import com.google.common.collect.Lists;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {AppConfig.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EventRESTServiceTest {

    private RestTemplate restTemplate = new RestTemplate();

    private static Event createdEvent;

    @Test
    public void test1createEvent() {
        Event event = new Event();
        event.setStart(LocalDateTime.of(2016, 10, 10, 19, 20));
        event.setEnd(LocalDateTime.of(2016, 10, 10, 20, 20));
        event.setBasePrice(100L);
        event.setName("Movie 43");
        event.setRating(Rating.LOW);
        Auditorium auditorium = new Auditorium();
        auditorium.setId(7L);
        event.setAuditorium(auditorium);
        event.setId(10L);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Lists.newArrayList(MediaType.APPLICATION_JSON));
        HttpEntity<Event> entity = new HttpEntity<>(event, headers);
        ResponseEntity<Event> eventEntity = restTemplate.exchange("http://localhost:8080/rest/event", HttpMethod.POST, entity, Event.class);
        createdEvent = eventEntity.getBody();
        System.out.println("\nEvent Service Client Test ");
        System.out.println("---------------------------- Test 1 ");
        System.out.println("Event " + createdEvent.getName() + "  created and obtained id " + createdEvent.getId());
        System.out.println("-------------------------------------");

    }


    @Test
    public void test2fetchEventById() {
        ResponseEntity<Event> eventEntity = restTemplate.getForEntity("http://localhost:8080/rest/event/{eventId}", Event.class, createdEvent.getId());
        Event eventById = eventEntity.getBody();
        System.out.println("---------------------------- Test 2 ");
        System.out.println("Event " + eventById.getName() + "  fetched by by id " + createdEvent.getId());
        System.out.println("-------------------------------------");
    }

    @Test
    public void test3RemoveEvent() {
        restTemplate.delete("http://localhost:8080/rest/event/{eventId}", createdEvent.getId());
        ResponseEntity<Event> eventEntity = restTemplate.getForEntity("http://localhost:8080/rest/event/{eventId}", Event.class, createdEvent.getId());
        System.out.println("---------------------------- Test 3 ");
        if (eventEntity.getStatusCode() == HttpStatus.NO_CONTENT) {
            System.out.println("Event " + createdEvent.getName() + "  removed successfully ");
        } else {
            System.out.println("Event " + createdEvent.getName() + "  NOT removed  ");
        }
        System.out.println("-------------------------------------");
    }


}
