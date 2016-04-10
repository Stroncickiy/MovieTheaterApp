
package com.epam.movies.ws.generated.events;

import com.epam.movies.model.Event;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for addEventResponse complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="addEventResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="createdEvent" type="{http://movietheater.epam.com}event"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addEventResponse", namespace = "http://movietheater.epam.com", propOrder = {
        "createdEvent"
})
public class AddEventResponse {

    @XmlElement(required = true)
    protected Event createdEvent;

    /**
     * Gets the value of the createdEvent property.
     *
     * @return possible object is
     * {@link Event }
     */
    public Event getCreatedEvent() {
        return createdEvent;
    }

    /**
     * Sets the value of the createdEvent property.
     *
     * @param value allowed object is
     *              {@link Event }
     */
    public void setCreatedEvent(Event value) {
        this.createdEvent = value;
    }

}
