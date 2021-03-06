package com.epam.movies.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class Auditorium {

    @Id
    private Long id;
    private String name;
    @JsonIgnore
    private List<Seat> seats;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Auditorium that = (Auditorium) o;

        return id != null ? id.equals(that.id) : that.id == null && (name != null ? name.equals(that.name) : that.name == null && (seats != null ? seats.equals(that.seats) : that.seats == null));

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (seats != null ? seats.hashCode() : 0);
        return result;
    }
}
