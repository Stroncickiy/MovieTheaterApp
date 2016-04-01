package com.epam.movies.model;

import com.epam.movies.discount.DiscountStrategy;

import javax.persistence.Id;
import java.util.List;


public class Ticket {
    @Id
    private Long id;
    private User customer;
    private Event event;
    private List<Seat> bookedSeats;
    private Long totalPrice;
    private DiscountStrategy  discountStrategy;
    private Long realPrice;
    private Long discountAmount;
    private boolean isLucky;


    public Long getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Long discountAmount) {
        this.discountAmount = discountAmount;
    }

    public boolean isLucky() {
        return isLucky;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public List<Seat> getBookedSeats() {
        return bookedSeats;
    }

    public void setBookedSeats(List<Seat> bookedSeats) {
        this.bookedSeats = bookedSeats;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public DiscountStrategy getDiscountStrategy() {
        return discountStrategy;
    }

    public void setDiscountStrategy(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }



    public Long getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(Long realPrice) {
        this.realPrice = realPrice;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ticket ticket = (Ticket) o;

        if (id != null ? !id.equals(ticket.id) : ticket.id != null) return false;
        return customer != null ? customer.equals(ticket.customer) : ticket.customer == null && (event != null ? event.equals(ticket.event) : ticket.event == null && (bookedSeats != null ? bookedSeats.equals(ticket.bookedSeats) : ticket.bookedSeats == null && (totalPrice != null ? totalPrice.equals(ticket.totalPrice) : ticket.totalPrice == null && (discountStrategy != null ? discountStrategy.equals(ticket.discountStrategy) : ticket.discountStrategy == null && (realPrice != null ? realPrice.equals(ticket.realPrice) : ticket.realPrice == null)))));

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (customer != null ? customer.hashCode() : 0);
        result = 31 * result + (event != null ? event.hashCode() : 0);
        result = 31 * result + (bookedSeats != null ? bookedSeats.hashCode() : 0);
        result = 31 * result + (totalPrice != null ? totalPrice.hashCode() : 0);
        result = 31 * result + (discountStrategy != null ? discountStrategy.hashCode() : 0);
        result = 31 * result + (realPrice != null ? realPrice.hashCode() : 0);
        return result;
    }

    public void makeLucky() {
        this.isLucky =true;
    }
}
