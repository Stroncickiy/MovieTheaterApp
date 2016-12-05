package com.movietheater.ws;

import com.epam.movietheater.EventServiceClient;
import com.epam.movietheater.configuration.AppConfig;
import com.epam.movietheater.generated.events.Auditorium;
import com.epam.movietheater.generated.events.Event;
import com.epam.movietheater.generated.events.Rating;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EventServiceTest {

    @Autowired
    private EventServiceClient eventServiceClient;

    private static Event createdEvent;

    @Test
    public void test1createEvent() {
        Auditorium auditorium = new Auditorium();
        auditorium.setName("Black Hole");
        auditorium.setId(7L);
        Event event = new Event();
        event.setName("5th Wave");
        event.setBasePrice(100L);
        event.setRating(Rating.HIGHT);
        event.setAuditorium(auditorium);
        createdEvent = eventServiceClient.createEvent(event);

        System.out.println("\nEvent Service Client Test ");
        System.out.println("---------------------------- Test 1 ");
        System.out.println("Event " + createdEvent.getName() + "  created and obtained id " + createdEvent.getId());
        System.out.println("-------------------------------------");
    }

    @Test
    public void test2fetchEventById() {
        Event eventById = eventServiceClient.getEventById(createdEvent.getId());
        System.out.println("---------------------------- Test 2 ");
        System.out.println("Event " + eventById.getName() + "  fetched by by id " + createdEvent.getId());
        System.out.println("-------------------------------------");
    }

    @Test
    public void test3RemoveEvent() {
        boolean removed = eventServiceClient.removeEvent(createdEvent.getId());
        System.out.println("---------------------------- Test 3 ");
        if (removed) {
            System.out.println("Event " + createdEvent.getName() + "  removed successfully ");
        } else {
            System.out.println("Event " + createdEvent.getName() + "  NOT removed  ");
        }
        System.out.println("-------------------------------------");
    }

}
