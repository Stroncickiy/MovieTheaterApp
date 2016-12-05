package com.epam.movietheater;

import com.epam.movietheater.generated.events.*;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import javax.xml.bind.JAXBElement;

public class EventServiceClient extends WebServiceGatewaySupport {

    private ObjectFactory objectFactory = new ObjectFactory();


    public Event createEvent(Event event) {
        AddEventRequest addEventRequest = new AddEventRequest();
        addEventRequest.setEvent(event);
        JAXBElement<AddEventRequest> addEventRequestJAXBElement = objectFactory.createAddEventRequest(addEventRequest);
        JAXBElement<AddEventResponse> addEventResponse = (JAXBElement<AddEventResponse>) getWebServiceTemplate().marshalSendAndReceive(addEventRequestJAXBElement);
        return addEventResponse.getValue().getCreatedEvent();
    }

    public Event getEventById(Long id) {
        GetEventByIdRequest getEventByIdRequest = new GetEventByIdRequest();
        getEventByIdRequest.setId(id);
        JAXBElement<GetEventByIdRequest> getEventByIdRequestJAXBElement = objectFactory.createGetEventByIdRequest(getEventByIdRequest);
        JAXBElement<GetEventByIdResponse> getEventByIdResponseJAXBElement = (JAXBElement<GetEventByIdResponse>) getWebServiceTemplate().marshalSendAndReceive(getEventByIdRequestJAXBElement);
        return getEventByIdResponseJAXBElement.getValue().getEvent();
    }


    public boolean removeEvent(Long id) {
        RemoveEventRequest removeEventRequest = new RemoveEventRequest();
        removeEventRequest.setId(id);
        JAXBElement<RemoveEventRequest> removeEventRequestJAXBElement = objectFactory.createRemoveEventRequest(removeEventRequest);
        JAXBElement<RemoveEventResponse> removeEventResponseJAXBElement = (JAXBElement<RemoveEventResponse>) getWebServiceTemplate().marshalSendAndReceive(removeEventRequestJAXBElement);
        return removeEventResponseJAXBElement.getValue().isRemoved();

    }


}
