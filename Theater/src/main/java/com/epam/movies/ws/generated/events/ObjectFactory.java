
package com.epam.movies.ws.generated.events;

import com.epam.movies.model.Auditorium;
import com.epam.movies.model.Event;
import com.epam.movies.model.Seat;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the com.epam.movies.ws.generated.events package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetEventByIdRequest_QNAME = new QName("http://movietheater.epam.com", "getEventByIdRequest");
    private final static QName _RemoveEventRequest_QNAME = new QName("http://movietheater.epam.com", "removeEventRequest");
    private final static QName _Event_QNAME = new QName("http://movietheater.epam.com", "event");
    private final static QName _Seat_QNAME = new QName("http://movietheater.epam.com", "seat");
    private final static QName _Auditorium_QNAME = new QName("http://movietheater.epam.com", "auditorium");
    private final static QName _GetEventByIdResponse_QNAME = new QName("http://movietheater.epam.com", "getEventByIdResponse");
    private final static QName _RemoveEventResponse_QNAME = new QName("http://movietheater.epam.com", "removeEventResponse");
    private final static QName _AddEventRequest_QNAME = new QName("http://movietheater.epam.com", "addEventRequest");
    private final static QName _AddEventResponse_QNAME = new QName("http://movietheater.epam.com", "addEventResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.epam.movies.ws.generated.events
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Seat }
     */
    public Seat createSeat() {
        return new Seat();
    }

    /**
     * Create an instance of {@link Auditorium }
     */
    public Auditorium createAuditorium() {
        return new Auditorium();
    }

    /**
     * Create an instance of {@link GetEventByIdResponse }
     */
    public GetEventByIdResponse createGetEventByIdResponse() {
        return new GetEventByIdResponse();
    }

    /**
     * Create an instance of {@link RemoveEventResponse }
     */
    public RemoveEventResponse createRemoveEventResponse() {
        return new RemoveEventResponse();
    }

    /**
     * Create an instance of {@link AddEventRequest }
     */
    public AddEventRequest createAddEventRequest() {
        return new AddEventRequest();
    }

    /**
     * Create an instance of {@link AddEventResponse }
     */
    public AddEventResponse createAddEventResponse() {
        return new AddEventResponse();
    }

    /**
     * Create an instance of {@link GetEventByIdRequest }
     */
    public GetEventByIdRequest createGetEventByIdRequest() {
        return new GetEventByIdRequest();
    }

    /**
     * Create an instance of {@link RemoveEventRequest }
     */
    public RemoveEventRequest createRemoveEventRequest() {
        return new RemoveEventRequest();
    }

    /**
     * Create an instance of {@link Event }
     */
    public Event createEvent() {
        return new Event();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetEventByIdRequest }{@code >}}
     */
    @XmlElementDecl(namespace = "http://movietheater.epam.com", name = "getEventByIdRequest")
    public JAXBElement<GetEventByIdRequest> createGetEventByIdRequest(GetEventByIdRequest value) {
        return new JAXBElement<GetEventByIdRequest>(_GetEventByIdRequest_QNAME, GetEventByIdRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveEventRequest }{@code >}}
     */
    @XmlElementDecl(namespace = "http://movietheater.epam.com", name = "removeEventRequest")
    public JAXBElement<RemoveEventRequest> createRemoveEventRequest(RemoveEventRequest value) {
        return new JAXBElement<RemoveEventRequest>(_RemoveEventRequest_QNAME, RemoveEventRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Event }{@code >}}
     */
    @XmlElementDecl(namespace = "http://movietheater.epam.com", name = "event")
    public JAXBElement<Event> createEvent(Event value) {
        return new JAXBElement<Event>(_Event_QNAME, Event.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Seat }{@code >}}
     */
    @XmlElementDecl(namespace = "http://movietheater.epam.com", name = "seat")
    public JAXBElement<Seat> createSeat(Seat value) {
        return new JAXBElement<Seat>(_Seat_QNAME, Seat.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Auditorium }{@code >}}
     */
    @XmlElementDecl(namespace = "http://movietheater.epam.com", name = "auditorium")
    public JAXBElement<Auditorium> createAuditorium(Auditorium value) {
        return new JAXBElement<Auditorium>(_Auditorium_QNAME, Auditorium.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetEventByIdResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://movietheater.epam.com", name = "getEventByIdResponse")
    public JAXBElement<GetEventByIdResponse> createGetEventByIdResponse(GetEventByIdResponse value) {
        return new JAXBElement<GetEventByIdResponse>(_GetEventByIdResponse_QNAME, GetEventByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveEventResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://movietheater.epam.com", name = "removeEventResponse")
    public JAXBElement<RemoveEventResponse> createRemoveEventResponse(RemoveEventResponse value) {
        return new JAXBElement<RemoveEventResponse>(_RemoveEventResponse_QNAME, RemoveEventResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddEventRequest }{@code >}}
     */
    @XmlElementDecl(namespace = "http://movietheater.epam.com", name = "addEventRequest")
    public JAXBElement<AddEventRequest> createAddEventRequest(AddEventRequest value) {
        return new JAXBElement<AddEventRequest>(_AddEventRequest_QNAME, AddEventRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddEventResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://movietheater.epam.com", name = "addEventResponse")
    public JAXBElement<AddEventResponse> createAddEventResponse(AddEventResponse value) {
        return new JAXBElement<AddEventResponse>(_AddEventResponse_QNAME, AddEventResponse.class, null, value);
    }

}
