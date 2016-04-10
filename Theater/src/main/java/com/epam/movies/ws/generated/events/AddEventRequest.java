
package com.epam.movies.ws.generated.events;

import com.epam.movies.model.Event;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for addEventRequest complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="addEventRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="event" type="{http://movietheater.epam.com}event"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addEventRequest", namespace = "http://movietheater.epam.com", propOrder = {
        "event"
})
public class AddEventRequest {

    @XmlElement(required = true)
    protected Event event;

    /**
     * Gets the value of the event property.
     *
     * @return possible object is
     * {@link Event }
     */
    public Event getEvent() {
        return event;
    }

    /**
     * Sets the value of the event property.
     *
     * @param value allowed object is
     *              {@link Event }
     */
    public void setEvent(Event value) {
        this.event = value;
    }

}
