package com.epam.movies.model;

import com.epam.movies.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.xml.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class User {
    @XmlAttribute
    private Long id;
    @XmlAttribute
    private String password;
    @XmlAttribute
    private String firstName;
    @XmlAttribute
    private String lastName;
    @XmlAttribute
    private String email;
    @XmlTransient
    private LocalDate birthDate;
    @XmlElement
    private List<UserRole> roles;
    @XmlAttribute
    private boolean enabled;


    public List<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        if (enabled != user.enabled) return false;
        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        return password != null ? password.equals(user.password) : user.password == null && (firstName != null ? firstName.equals(user.firstName) : user.firstName == null && (lastName != null ? lastName.equals(user.lastName) : user.lastName == null && (email != null ? email.equals(user.email) : user.email == null && (birthDate != null ? birthDate.equals(user.birthDate) : user.birthDate == null && (roles != null ? roles.equals(user.roles) : user.roles == null)))));

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        result = 31 * result + (roles != null ? roles.hashCode() : 0);
        result = 31 * result + (enabled ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{email=" + email + "}";
    }

}
