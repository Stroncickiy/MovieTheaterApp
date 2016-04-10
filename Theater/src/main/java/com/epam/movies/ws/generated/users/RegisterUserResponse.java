
package com.epam.movies.ws.generated.users;

import com.epam.movies.model.User;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for registerUserResponse complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="registerUserResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="createdUser" type="{http://movietheater.epam.com}user"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "registerUserResponse", namespace = "http://movietheater.epam.com", propOrder = {
        "createdUser"
})
public class RegisterUserResponse {

    @XmlElement(required = true)
    protected User createdUser;

    /**
     * Gets the value of the createdUser property.
     *
     * @return possible object is
     * {@link User }
     */
    public User getCreatedUser() {
        return createdUser;
    }

    /**
     * Sets the value of the createdUser property.
     *
     * @param value allowed object is
     *              {@link User }
     */
    public void setCreatedUser(User value) {
        this.createdUser = value;
    }

}
