package com.epam.movies.ws.services;

import com.epam.movies.model.Event;
import com.epam.movies.service.EventService;
import com.epam.movies.ws.generated.events.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.bind.JAXBElement;
import java.time.LocalDateTime;

@Endpoint
public class EventEndpoint {
    private static final String NAMESPACE_URI = "http://movietheater.epam.com";

    @Autowired
    private EventService eventService;

    private ObjectFactory objectFactory = new ObjectFactory();

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addEventRequest")
    @ResponsePayload
    public JAXBElement<AddEventResponse> addEvent(@RequestPayload JAXBElement<AddEventRequest> addEventRequestJAXBElement) {
        Event event = addEventRequestJAXBElement.getValue().getEvent();
        event.setStart(LocalDateTime.of(2016, 10, 10, 21, 30));
        event.setEnd(LocalDateTime.of(2016, 10, 10, 23, 30));
        Event addedEvent = eventService.create(event);
        AddEventResponse addEventResponse = new AddEventResponse();
        addEventResponse.setCreatedEvent(addedEvent);
        return objectFactory.createAddEventResponse(addEventResponse);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEventByIdRequest")
    @ResponsePayload
    public JAXBElement<GetEventByIdResponse> getEventById(@RequestPayload JAXBElement<GetEventByIdRequest> eventByIdRequestJAXBElement) {
        Event eventById = eventService.getById(eventByIdRequestJAXBElement.getValue().getId());
        GetEventByIdResponse getEventByIdResponse = new GetEventByIdResponse();
        getEventByIdResponse.setEvent(eventById);
        return objectFactory.createGetEventByIdResponse(getEventByIdResponse);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "removeEventRequest")
    @ResponsePayload
    public JAXBElement<RemoveEventResponse> removeEvent(@RequestPayload JAXBElement<RemoveEventRequest> removeEventRequestJAXBElement) {
        boolean removed = eventService.remove(removeEventRequestJAXBElement.getValue().getId());
        RemoveEventResponse removeEventResponse = new RemoveEventResponse();
        removeEventResponse.setRemoved(removed);
        return objectFactory.createRemoveEventResponse(removeEventResponse);

    }


}
